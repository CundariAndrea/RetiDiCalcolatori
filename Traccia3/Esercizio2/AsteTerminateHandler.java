package Traccia3.Esercizio2;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class AsteTerminateHandler extends Thread{
    private Integer udpPort;
    private LinkedList<Asta> asteTerminate;
    private HashMap<Asta,LinkedList<Offerta>> offerte;

    public AsteTerminateHandler(Integer udpPort, LinkedList<Asta> asteTerminate, HashMap<Asta, LinkedList<Offerta>> offerte) {
        this.udpPort = udpPort;
        this.asteTerminate = asteTerminate;
        this.offerte = offerte;
    }
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(udpPort);
            Socket socket = serverSocket.accept();
            for(Asta a : asteTerminate){
                String vincitore=CalcolaVincitore(a);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(vincitore);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String CalcolaVincitore(Asta a) {
        String ris="";
        Double max=(double)-1;
        for(Asta a1:offerte.keySet()){
            if(a.equals(a1)){
                LinkedList<Offerta> offerta = offerte.get(a1);
                for(Offerta o:offerta){
                    if(o.getImporto()>max){
                        max=o.getImporto();
                        ris=o.getCf();
                    }
                }
            }
        }
        return ris;
    }
}
