package Traccia3.Esercizio2;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;

public class AsteHandler extends Thread{
    private Integer tcpPort;
    private Asta asta;
    private HashMap<Asta, Timestamp> asteAttive;
    private Timestamp currentTime;
    private final Integer maxTime=60; //in minuti
    private final Integer udpPort=5000;
    private final String udpLink="230.0.0.1";
    private ServerSocket serverSocket;
    private Socket socket;
    private LinkedList<Asta> asteTerminate;

    public AsteHandler(Integer tcpPort, Asta asta, HashMap<Asta, Timestamp> asteAttive,LinkedList<Asta> asteTerminate) {
        this.tcpPort =tcpPort;
        this.asta = asta;
        this.asteAttive = asteAttive;
        this.asteTerminate = asteTerminate;
    }

    public void run() {
        try {
            currentTime = new Timestamp(System.currentTimeMillis());
            if (!asteAttive.containsKey(asta)) {
                asteAttive.put(asta, currentTime);
                serverSocket = new ServerSocket(tcpPort);
                socket=serverSocket.accept();
                DatagramSocket datagramSocket = new DatagramSocket(udpPort);
                StringBuilder sb=new StringBuilder();
                sb.append(asta).append(",").append(tcpPort);
                String messaggio=sb.toString();
                byte [] buf=messaggio.getBytes();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                ObjectOutputStream oos=new ObjectOutputStream(baos);
                oos.writeObject(messaggio);
                oos.close();
                baos.close();
            }else{
                if(asteAttive.get(asta).getTime()<currentTime.getTime()){
                    socket.close();
                    asteAttive.remove(asta);
                    asteTerminate.addLast(asta);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
