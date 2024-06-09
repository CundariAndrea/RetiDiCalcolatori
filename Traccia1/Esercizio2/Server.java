package Traccia1.Esercizio2;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;

public class Server {
    private final static int rPort=3000;
    private final static String gAddress="230.0.0.1";
    private final static int gPort=5000;
    private final static int sPort=4000;
    private Registro registro;
    private LinkedList<Integer> nonFunzionanti;

    public Server(){
        this.registro= new Registro();
        this.nonFunzionanti=new LinkedList<Integer>();
    }

    public Registro getRegistro() {
        return registro;
    }

    public LinkedList<Integer> getNonFunzionanti() {
        return nonFunzionanti;
    }

    public static void main(String...args){
        Server server=new Server();
        try{
            ServerSocket s=new ServerSocket(rPort);
            DatagramSocket sSensore=new DatagramSocket(sPort);
            while (true){
                Socket richiesta=s.accept();
                RichiestaHandler rh=new RichiestaHandler(richiesta,server.getRegistro());
                rh.start();
                SensorHandler sh=new SensorHandler(sSensore,server.getRegistro(),server.getNonFunzionanti());
                sh.start();
                for(Integer i: server.getRegistro().getMap().keySet()){
                    sh.CheckTimeot(i);
                }
                if(!server.getNonFunzionanti().isEmpty()){
                    //manda un messaggio
                    MulticastSocket ms=new MulticastSocket();
                    StringBuilder sb=new StringBuilder();
                    //creo il messaggio
                    for(Integer id: server.getNonFunzionanti()){
                        sb.append(id.toString()).append(",");
                    }
                    //lo trasformo in byte
                    String messaggio=sb.toString();
                    byte[] buffer=messaggio.getBytes();
                    InetAddress gruppo=InetAddress.getByName(gAddress);
                    DatagramPacket packet=new DatagramPacket(buffer,buffer.length,gruppo,gPort);
                    ms.send(packet);
                    ms.close(); //non so se va fatto qui
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}