package Traccia7.Esercizio2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;

public class PrenotazioneHandler extends Thread{
    private final Integer tcpPort;
    LinkedList<EsameClinico> esami;
    LocalTime ora;
    HashMap<EsameClinico, LinkedList<InetAddress>> accodati;
    public PrenotazioneHandler(LinkedList<EsameClinico> esami,Integer tcpPort) {
        this.esami = esami;
        this.tcpPort = tcpPort;
        accodati = new HashMap<>();
    }

    public void run() {
        try{
            while(true){
                ServerSocket ss = new ServerSocket(tcpPort);
                Socket s = ss.accept();
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                String codiceEsame=(String) ois.readObject();
                ora=LocalTime.now();
                if(ora.isAfter(LocalTime.parse("12:00"))|| ora.isBefore(LocalTime.parse("8:00"))){
                    String messaggio="Service not available";
                    ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(messaggio);
                    oos.flush();
                    oos.close();
                }else{
                    if(Prenotabile(codiceEsame)){
                       Medico m=Prenota(codiceEsame,s.getInetAddress());
                       Integer numPren=CalcolaNumPrenotazione(codiceEsame,s.getInetAddress());
                       String messaggio;
                       StringBuilder sb=new StringBuilder();
                       sb.append("Matricola: \n").append(m.getMatricola())
                               .append("\n numero prenotazione: \n").append(numPren);
                       messaggio=sb.toString();
                       ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                       oos.writeObject(messaggio);
                       oos.flush();
                       oos.close();
                    }else{
                        AggiungiInCoda(codiceEsame,s.getInetAddress());
                        AttendiInCoda(codiceEsame,s.getInetAddress(),s);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private synchronized void EliminaDaAccodati(String codiceEsame,InetAddress ip){
        for(EsameClinico e : accodati.keySet()){
            if(e.getCodice().equals(codiceEsame)){
                LinkedList<InetAddress> coda=accodati.get(e);
                coda.remove(ip);
                accodati.put(e,coda);
            }
        }
    }

    private synchronized void AggiungiInCoda(String codiceEsame,InetAddress inetAddress){
        boolean prenotato=false;
        for(EsameClinico e:accodati.keySet()){
            if(e.getCodice().equals(codiceEsame)){
                LinkedList<InetAddress> lista=accodati.get(e);
                lista.addLast(inetAddress);
                accodati.put(e,lista);
                prenotato=true;
            }
        }
        if(!prenotato){
            LinkedList<InetAddress> lista=new LinkedList<>();
            lista.add(inetAddress);
            for(EsameClinico e:esami){
                if(e.getCodice().equals(codiceEsame)){
                    accodati.put(e,lista);
                }
            }
        }
    }

    private void AttendiInCoda(String codiceEsame,InetAddress inetAddress,Socket socket){
        LocalTime temp=LocalTime.now();
        boolean prenotato=false;
        while(Duration.between(temp, LocalTime.now()).toHours() <= 1){
            try{
                if(!prenotato){
                    Thread.sleep(20*60*1000); //prova a prenotarsi ad intervalli di 20 minuti
                    /* ho deciso di gestirla cos' perchè non era specificato in maniera concreta come andava fatto
                    e ho pensato che una versione iper persistente in cui il thread prova a prenotarsi ogni secondo
                    non fosse molto efficente, dato che il metodo "Prenotabile" è dichiarato syncronized (e quindi
                    puo essere eseguito da un thred per volta) così facendo esistono dei casi in cui è prenotabile
                    ma non viene prenotato perchè qualcuno arriva prima di lui, questo potrebbe essere migliorato
                    utilizzando un sistema simile a quello dei listener o utilizzando un metodo che verifica se
                    esistono degli accodati prima di prenotare il cliente attuale ma per mancanza di tempo all'esame
                    è infattibile.
                     */
                    prenotato=Prenotabile(codiceEsame);
                }else{
                    Medico m=Prenota(codiceEsame,inetAddress);
                    int np=CalcolaNumPrenotazione(codiceEsame,inetAddress);
                    String messaggio;
                    StringBuilder sb=new StringBuilder();
                    sb.append("Matricola: \n").append(m.getMatricola());
                    sb.append("\n numero prenotazione: \n").append(np);
                    messaggio=sb.toString();
                    ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(messaggio);
                    oos.close();
                    break;
                }
            }catch (InterruptedException | IOException e){
                e.printStackTrace();
            }
        }
        if(!prenotato){
            EliminaDaAccodati(codiceEsame,inetAddress);
            String messaggio="Impossibile prenotare per la giornata";
            try{
                ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(messaggio);
                oos.close();
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private synchronized Integer CalcolaNumPrenotazione(String codiceEsame, InetAddress inetAddress) {
        Integer numPren=0;
        for(EsameClinico e : esami){
            if(e.getCodice().equals(codiceEsame)){
                numPren= e.getPrenotazione(inetAddress);
            }
        }
        return numPren;
    }

    private synchronized Medico Prenota(String codiceEsame, InetAddress inetAddress) {
        EliminaDaAccodati(codiceEsame,inetAddress);
        Medico ris=null;
        for(EsameClinico e: esami){
            if(e.getCodice().equals(codiceEsame)){
                if(e.getPrenotati().get(e.getMedico1()).size() > e.getPrenotati().get(e.getMedico2()).size()){
                    e.getPrenotati().get(e.getMedico1()).addLast(inetAddress);
                    e.addPrenotazione(inetAddress);
                    ris=e.getMedico1();
                }else {
                    e.getPrenotati().get(e.getMedico2()).addLast(inetAddress);
                    e.addPrenotazione(inetAddress);
                    ris=e.getMedico2();
                }
            }
        }
        return ris;
    }

    private synchronized boolean Prenotabile(String codiceEsame) {
        boolean ris=false;
        for(EsameClinico e : esami){
            if(e.getCodice().equals(codiceEsame)){
                Medico m1=e.getMedico1();
                Medico m2=e.getMedico2();
                LinkedList<InetAddress> p1=e.getPrenotati().get(m1);
                LinkedList<InetAddress> p2=e.getPrenotati().get(m2);
                if(p1.size()+p2.size()<20){
                    ris=true;
                }
            }
        }
        return ris;
    }
}