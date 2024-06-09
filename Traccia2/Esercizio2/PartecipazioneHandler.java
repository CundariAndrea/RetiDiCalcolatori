package Traccia2.Esercizio2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;

public class PartecipazioneHandler extends Thread{
    private LinkedList<Concorso> concorsi;
    private HashMap<Concorso,LinkedList<Partecipazione>> partecipazioni;
    private HashMap<Integer,Partecipazione> domande;
    private Socket socket;
    private int idDomanda =0;
    private Timestamp orario;
    private Concorso c1;

    public PartecipazioneHandler(LinkedList<Concorso> concorsi,HashMap<Concorso,
            LinkedList<Partecipazione>> partecipazioni,HashMap<Integer,Partecipazione> domande,Socket socket) {
        this.concorsi = concorsi;
        this.partecipazioni = partecipazioni;
        this.socket = socket;
        this.domande = domande;
    }

    @Override
    public void run(){
        try {
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream ous=new ObjectOutputStream(socket.getOutputStream());
            Partecipazione p=(Partecipazione) ois.readObject();
            orario= new Timestamp(System.currentTimeMillis());
            for(Concorso c:concorsi){
                if(c.getId()==p.getId()){
                    c1=c;
                    break;
                }
            }
            if( p.getNome()==null ||p.getCognome()==null||
                    p.getCodiceFiscale()==null|| p.getId()==null || p.getCurriculum()==null ||
                    orario.getTime()>c1.getDataScadenza().getTime() ){
                    String messaggio = "NOT_ACCEPTED";
                    ous.writeObject(messaggio);
            }else {
                LinkedList<Partecipazione> tmp=partecipazioni.get(c1);
                tmp.addLast(p);
                partecipazioni.put(c1,tmp);
                domande.put(idDomanda,p);
                StringBuilder sb=new StringBuilder();
                sb.append(idDomanda).append(orario.toString());
                idDomanda++;
                ous.writeObject(sb.toString());
            }
            ois.close();
            ous.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
