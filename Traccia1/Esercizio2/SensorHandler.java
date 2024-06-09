package Traccia1.Esercizio2;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;

public class SensorHandler extends Thread{
    private DatagramSocket socket;
    private Registro registro;
    private LinkedList<Integer> nonFunzionanti;
    private HashMap<Integer,Timestamp> orari=new HashMap<>();
    private final int MAX_INTERVALLO=10;

    public SensorHandler(DatagramSocket socket, Registro registro, LinkedList<Integer> nonFunzionanti){
        this.socket=socket;
        this.registro=registro;
        this.nonFunzionanti=nonFunzionanti;
    }
    public void run(){
        try{
            byte [] buf=new byte[512];
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            socket.receive(p);
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object receivedObject = ois.readObject();
            ois.close();
            Misura m=(Misura)  receivedObject;
            registro.add(m.getIdSensore(),m);
            if(!CheckTimeot(m.getIdSensore())){
                nonFunzionanti.add(m.getIdSensore());
            }
        }catch (Exception e){
          System.out.println(e.toString());
        }
    }

    public boolean CheckTimeot(Integer idSensore){
        Timestamp misurazioneAttuale=new Timestamp(System.currentTimeMillis());
        Timestamp ultimaMisurazione=orari.get(idSensore); // può essere null!!!
        if(ultimaMisurazione==null){
            orari.put(idSensore,misurazioneAttuale);
            return true;
        }
        long tempoTrascorso=misurazioneAttuale.getTime()-ultimaMisurazione.getTime();
        long minuti=tempoTrascorso/(60*100); //converto in minuti per semplicità
        if(minuti<=MAX_INTERVALLO){
            orari.put(idSensore,misurazioneAttuale);
            return true;
        }else{
            return false;
        }
    }
}
