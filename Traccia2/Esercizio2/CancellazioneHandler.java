package Traccia2.Esercizio2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.LinkedList;

public class CancellazioneHandler extends Thread {
    private HashMap<Integer, Partecipazione> domande;
    private LinkedList<Concorso> concorsi;
    private HashMap<Concorso,LinkedList<Partecipazione>> partecipazioni;
    private DatagramSocket socket;
    public CancellazioneHandler(LinkedList<Concorso> concorsi,
                                HashMap<Concorso,LinkedList<Partecipazione>> partecipazioni,
            HashMap<Integer,Partecipazione> domande, DatagramSocket socket ) {
        this.concorsi = concorsi;
        this.partecipazioni = partecipazioni;
        this.socket = socket;
        this.domande=domande;
    }


    public void run() {
        try {
            byte[] buffer = new byte[512];
            DatagramPacket dp=new DatagramPacket(buffer,buffer.length);
            socket.receive(dp);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object receivedObject = ois.readObject();
            ois.close();
            Integer idDomanda=(Integer)receivedObject;
            Partecipazione p=domande.get(idDomanda);
            if(p==null){
                boolean messaggio=false;
                ByteArrayOutputStream baos= new ByteArrayOutputStream();
                ObjectOutputStream oos= new ObjectOutputStream(baos);
                oos.writeObject(messaggio);
                oos.close();
                baos.close();
            }else{
                domande.remove(idDomanda);
                int idConcorso=p.getId();
                for(Concorso c:concorsi){
                    if(c.getId()==idConcorso){
                        LinkedList<Partecipazione> tmp=partecipazioni.get(c);
                        boolean messaggio=tmp.remove(p);
                        ByteArrayOutputStream baos= new ByteArrayOutputStream();
                        ObjectOutputStream oos= new ObjectOutputStream(baos);
                        oos.writeObject(messaggio);
                        oos.close();
                        baos.close();
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
