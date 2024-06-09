package Traccia2.Esercizio2;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class Server {
    private final int portTCP=3000;
    private final int portUDP=4000;
    private final int portMulticast=5000;
    private final String indirizzoMulticast="230.0.0.1";
    private LinkedList<Concorso> concorsi;
    private HashMap<Concorso,LinkedList<Partecipazione>> partecipazioni;
    private HashMap<Integer,Partecipazione> domande;

    public LinkedList<Concorso> getConcorsi() {
        return concorsi;
    }

    public HashMap<Concorso, LinkedList<Partecipazione>> getPartecipazioni() {
        return partecipazioni;
    }

    public HashMap<Integer, Partecipazione> getDomande() {
        return domande;
    }

    public static void main(String...Args){
        Server server = new Server();
        try{
            ServerSocket ServerSocket=new ServerSocket(server.portTCP); //tcp
            DatagramSocket DatagramSocket=new DatagramSocket(server.portUDP); //udp
            while (true){
                //parte tcp
                Socket socket=ServerSocket.accept();
                PartecipazioneHandler ph=new PartecipazioneHandler(server.getConcorsi(),
                        server.getPartecipazioni(),server.getDomande(),socket);
                ph.start();
                //parte UDP
                CancellazioneHandler ch =new CancellazioneHandler(server.getConcorsi(),server.getPartecipazioni(),
                        server.getDomande(),DatagramSocket);
                ch.start();
                //parte mutlicast
                ConcorsiAttiviHandler cah=new ConcorsiAttiviHandler(server.getConcorsi(),server.getPartecipazioni()
                        ,server.portMulticast,server.indirizzoMulticast);
                cah.start();
            }
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}