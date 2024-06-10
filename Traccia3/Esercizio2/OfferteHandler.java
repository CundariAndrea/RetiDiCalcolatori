package Traccia3.Esercizio2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;

public class OfferteHandler extends Thread{
    private Integer tcpPort;
    private Socket socket;
    private HashMap<Asta, LinkedList<Offerta>> offerte;
    private HashMap<Asta,Timestamp> asteAttive;
    private boolean messaggio=false;

    public OfferteHandler(Integer tcpPort, HashMap<Asta,LinkedList<Offerta>> offerte, HashMap<Asta, Timestamp> aste) {
        this.tcpPort = tcpPort;
        this.offerte = offerte;
        this.asteAttive = aste;
    }

    public void run(){
        try{
            ServerSocket serverSocket = new ServerSocket(tcpPort);
            socket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Offerta offerta = (Offerta) ois.readObject();
            for(Asta asta:asteAttive.keySet()){
                if(asta.getIdAsta().equals(offerta.getIdAsta())){
                    if(offerte.containsKey(asta)){
                        for(Asta asta1: asteAttive.keySet()){
                            if(asta1.getIdAsta().equals(offerta.getIdAsta())){
                                LinkedList<Offerta> tmp=offerte.get(offerta.getIdAsta());
                                tmp.addLast(offerta);
                                offerte.put(asta1,tmp);
                                messaggio=true;
                            }
                        }
                    }else{
                        LinkedList<Offerta> tmp=new LinkedList<>();
                        for(Asta asta1: asteAttive.keySet()){
                            if(asta1.getIdAsta().equals(offerta.getIdAsta())){
                                tmp.addLast(offerta);
                                offerte.put(asta1,tmp);
                                messaggio=true;
                            }
                        }
                    }
                }
            }
            if(!messaggio){
                //genera messaggio false
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(messaggio);
                oos.close();
            }else{
                //genera messaggio true
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(messaggio);
                oos.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}