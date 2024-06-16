package Traccia2020;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class RichiesteHandler extends Thread{
    private LinkedList<Richiesta> richieste;
    private LinkedList<Offerta> offerte;
    private final int inputTCP;
    private final int outputTCP;
    private final int MulticastPort;
    private final String indirizzoMulticast;
    private Timestamp currentTime;
    public RichiesteHandler(LinkedList<Richiesta> richiesteDaGestire,int inputTCP,int outputTCP,
                            String indirizzoMulticast, int MulticastPort,LinkedList<Offerta> offerte) {
        this.richieste = richiesteDaGestire;
        this.inputTCP = inputTCP;
        this.MulticastPort = MulticastPort;
        this.indirizzoMulticast = indirizzoMulticast;
        this.outputTCP = outputTCP;
        this.offerte = offerte;
    }

    public void run() {
        try{
            if(!richieste.isEmpty()) {
                String messaggio;
                Richiesta r=richieste.removeLast();
                StringBuilder sb=new StringBuilder();
                sb.append("<").append(r.getData()).append(">,<").append(r.getNumeroPersone()).append(">");
                messaggio=sb.toString();
                MulticastSocket multicastSocket=new MulticastSocket(MulticastPort);
                InetAddress grout=InetAddress.getByName(indirizzoMulticast);
                byte[] buf=messaggio.getBytes();
                DatagramPacket packet=new DatagramPacket(buf,buf.length,grout,MulticastPort);
                multicastSocket.send(packet);
                currentTime=new Timestamp(System.currentTimeMillis());
                int min= (int) (currentTime.getTime()/(1000*60));
                ServerSocket serverSocket=new ServerSocket(inputTCP);
                Socket socket=serverSocket.accept();
                ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                while (min>=((int) System.currentTimeMillis()/(1000*60)) ){
                    String ricevuta=(String) ois.readObject();
                    StringTokenizer st=new StringTokenizer(ricevuta,",");
                    String hostName=st.nextToken().replace("<","")
                            .replace(">","");
                    Double prezzo=Double.valueOf(st.nextToken().replace("<","")
                            .replace(">",""));
                    Offerta offerta=new Offerta(hostName,prezzo);
                    offerte.add(offerta);
                }
                for(Offerta o:offerte){
                    String offerta;
                    StringBuilder sb1=new StringBuilder();
                    sb1.append("<").append(o.getHostnameCentroBenessere())
                            .append(">,<").append(o.getPrezzo()).append(">");
                    offerta =sb1.toString();
                    oos.writeObject(offerta);
                }
                oos.close();
                ois.close();
                socket.close();
                multicastSocket.close();
                serverSocket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
