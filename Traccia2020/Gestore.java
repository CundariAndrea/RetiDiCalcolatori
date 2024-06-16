package Traccia2020;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Gestore {
    private final String indirizzoMulticast="224.3.2.1";
    private final int multicastPort=2222;
    private final int outputTCP=3333;
    private final int inputTcp=1111;
    private LinkedList<Richiesta> richiesteDaGestire;
    private LinkedList<Offerta> offerte;
    public Gestore() {
        this.richiesteDaGestire = new LinkedList<>();
        offerte=new LinkedList<>();
    }

    public static void Main(String...Args){
        Gestore gestore=new Gestore();
        while (true){
            try{
                ServerSocket serverSocket=new ServerSocket(gestore.inputTcp);
                Socket socket=serverSocket.accept();
                ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                String richiesta=(String)ois.readObject();
                StringTokenizer st=new StringTokenizer(richiesta,",");
                String data=st.nextToken().replace("<","").replace(">","");
                Date d=new Date(data);
                String np=st.nextToken().replace("<","").replace(">","");
                Richiesta r=new Richiesta(d,Integer.parseInt(np));
                gestore.richiesteDaGestire.addLast(r);
                RichiesteHandler rh=new RichiesteHandler(gestore.richiesteDaGestire,gestore.inputTcp,
                        gestore.outputTCP,gestore.indirizzoMulticast,gestore.multicastPort,gestore.offerte);
                rh.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}