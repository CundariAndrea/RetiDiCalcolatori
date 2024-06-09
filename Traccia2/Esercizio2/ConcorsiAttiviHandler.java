package Traccia2.Esercizio2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;

public class ConcorsiAttiviHandler extends Thread{
    private LinkedList<Concorso> concorsi;
    private int portaMulticast;
    private String indirizzoMulticast;
    private HashMap<Concorso,LinkedList<Partecipazione>> partecipazioni;
    private Timestamp currentTime;

    public ConcorsiAttiviHandler(LinkedList<Concorso> concorsi, HashMap<Concorso,LinkedList<Partecipazione>> partecipazioni,
                                 int portaMulticast, String indirizzoMulticast) {
        this.portaMulticast = portaMulticast;
        this.concorsi = concorsi;
        this.indirizzoMulticast = indirizzoMulticast;
        this.partecipazioni = partecipazioni;
    }

    @Override
    public void run() {
        try {
            currentTime=new Timestamp(System.currentTimeMillis());
            MulticastSocket socket=new MulticastSocket();
            for(Concorso c: concorsi){
                if (c.getDataScadenza().getTime()<currentTime.getTime()){
                    LinkedList<String> vincitori=GeneraVincitori(c);
                    StringBuilder sb=new StringBuilder();
                    sb.append(c.getId());
                    for(String v: vincitori){
                        sb.append(',').append(v);
                    }
                    String messaggio=sb.toString();
                    byte[] buf=messaggio.getBytes();
                    InetAddress gruppo=InetAddress.getByName(indirizzoMulticast);
                    DatagramPacket dp=new DatagramPacket(buf,buf.length,gruppo,portaMulticast);
                    socket.send(dp);
                    socket.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private LinkedList<String> GeneraVincitori(Concorso c) {
        LinkedList<String> vincitori=new LinkedList<>();
        LinkedList<Partecipazione> tmp=partecipazioni.get(c);
        int nVinc=c.getNumeroPosti();
        for(int i=0; i<nVinc; i++){
            vincitori.addLast(tmp.get(i).getCodiceFiscale());
        }
        return vincitori;
    }
}
