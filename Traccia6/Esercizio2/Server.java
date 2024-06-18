package Traccia6.Esercizio2;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.LinkedList;

public class Server {
    private final Integer tcpInput=3000;
    private final Integer tcpNotifica=4000;
    private final Integer udpPort=4000;
    private LinkedList<Canzone> canzoni;
    private Integer idCanzone=0;
    private HashMap<InetAddress,Richiesta> richieste;

    public Server() {
        canzoni = new LinkedList<>();
        richieste = new HashMap<>();
    }
    public static void main(String[] args) {
        Server server = new Server();
        try{
            while(true){
                CanzoneHandler ch=new CanzoneHandler(server.tcpInput,server.idCanzone,server.canzoni);
                ch.start();
                NotificaHandler nh=new NotificaHandler(server.tcpNotifica,server.udpPort,server.canzoni,server.richieste);
                nh.start();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
