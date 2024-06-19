package Traccia5.Esercizio2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.HashMap;
import java.util.LinkedList;

public class NotificaHandler extends Thread {
    private LinkedList<StatoSensore> stati;
    private HashMap<Integer, String > link;
    private LinkedList<Integer> iscritti;
    private StatoSensore prec;


    public NotificaHandler(LinkedList<StatoSensore> stati, HashMap<Integer, String> link,StatoSensore prec) {
        this.stati = stati;
        this.link = link;
        this.iscritti = new LinkedList<>();
        this.prec = prec;
    }

    public void run() {
        while(true) {
            if(!iscritti.contains(prec.getIdSensore())){
                iscritti.add(prec.getIdSensore());
            }
            if(prec.getIdSensore()!=stati.getLast().getIdSensore()){
                prec=stati.getLast();
                for(StatoSensore s: stati){
                    if(s.getIdSensore()!= prec.getIdSensore()){
                        if(iscritti.contains(s.getIdSensore())){
                            for(Integer i: link.keySet()){
                                if(s.getIdSensore()==i){
                                    try{
                                        DatagramSocket socket = new DatagramSocket();
                                        StringBuilder sb=new StringBuilder();
                                        sb.append(s.getIdSensore()).append("#").append(s.getNp()).append("#").append(s.getTemperatura()).append(",").append(s.getUmidita());
                                        String messagio=sb.toString();
                                        byte [] buff=messagio.getBytes();
                                        DatagramPacket packet=new DatagramPacket(buff,buff.length, InetAddress.getByName(link.get(i)),4000);
                                        socket.send(packet);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
