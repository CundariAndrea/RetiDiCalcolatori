package Traccia6.Esercizio2;

import java.io.ObjectInputStream;
import java.net.*;
import java.util.HashMap;
import java.util.LinkedList;

public class NotificaHandler extends Thread {
    Integer tcpNotifica;
    Integer udpPort;
    LinkedList<Canzone> canzoni;
    HashMap<InetAddress,Richiesta> richieste;

    public NotificaHandler(Integer tcpNotifica, Integer udpPort, LinkedList<Canzone> canzoni,
                           HashMap<InetAddress, Richiesta> richieste) {
        this.tcpNotifica = tcpNotifica;
        this.udpPort = udpPort;
        this.canzoni = canzoni;
        this.richieste = richieste;
    }

    private boolean DaNotificare(String testo,LinkedList<String> autori, InetAddress i){
        boolean ris=false;
        String autore=richieste.get(i).getNomeAutore();
        LinkedList<String> paroleChiave=richieste.get(i).getParoleChiave();
        for(String s:paroleChiave){
            if(testo.contains(s)){
                ris=true;
            }
        }
        for(String a:autori){
            if(a.equals(autore)){
                ris=true;
            }
        }
        return ris;
    }

    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(tcpNotifica);
            Socket socket=serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Richiesta r=(Richiesta) ois.readObject();
            InetAddress id=socket.getInetAddress();
            richieste.put(id,r);
            for(InetAddress i: richieste.keySet()){
                for(Canzone c:canzoni){
                    String testo=c.getTesto();
                    LinkedList<String> autori=c.getAutori();
                    if(DaNotificare(testo,autori,i)){
                        DatagramSocket ds=new DatagramSocket();
                        String messaggio;
                        StringBuilder sb=new StringBuilder();
                        sb.append(c.getId()).append("#").append(c.getTitolo()).append("#");
                        for(String autore: c.getAutori()){
                            sb.append(autore).append("#");
                        }
                        messaggio=sb.toString();
                        byte[] msg=messaggio.getBytes();
                        DatagramPacket dp=new DatagramPacket(msg,msg.length,id,udpPort);
                        ds.send(dp);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
