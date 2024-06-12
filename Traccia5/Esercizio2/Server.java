package Traccia5.Esercizio2;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;

public class Server {
    private final int ORA_INIZIALE=8;
    private final int ORA_FINALE=13;
    private final int tcpPort=3000;
    private final int tcpPort2=4000;
    private final int udpPort=4000;
    private int np;
    private LinkedList<StatoSensore> stati;
    private HashMap<Integer,String> link;

    public Server() {
        stati=new LinkedList<>();
        link=new HashMap<>();
        np=0;
    }

    public static void main(String[] args) {
        Server server = new Server();
        boolean ok=false;
        while(true) {
            if(!server.stati.isEmpty()&&!ok){
                NotificaHandler nh=new NotificaHandler(server.stati,server.link,server.stati.getLast());
                nh.start();
                ok=true;
            }
            try {
                RichiestaHandler rh=new RichiestaHandler(server.stati,server.tcpPort,server.ORA_INIZIALE,server.ORA_FINALE,server.np,server.link);
                rh.start();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
