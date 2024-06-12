package Traccia5.Esercizio2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;

public class RichiestaHandler extends Thread{
    private final int tcpPort;
    private final int ORA_INIZIALE;
    private final int ORA_FINALE;
    private HashMap<Integer, String> link;
    private LinkedList<StatoSensore> stati;
    private Timestamp oraCorrente;
    private int np;

    public RichiestaHandler(LinkedList<StatoSensore> stati, int tcpPort, int ORA_INIZIALE, int ORA_FINALE, int np, HashMap<Integer,String> link) {
        this.tcpPort = tcpPort;
        this.stati = stati;
        this.ORA_INIZIALE = ORA_INIZIALE;
        this.ORA_FINALE = ORA_FINALE;
        this.link=link;
        this.np = np;
    }


    private boolean ControllaVariazione(Double mediaUmidita,Double mediaTemperatura,Double temperatura, double umidita ){
        Double lbUmidita = mediaUmidita*0.95;
        Double upUmidita= mediaUmidita*1.05;
        Double lbTemperatura = mediaTemperatura*0.95;
        Double upTemperatura= mediaTemperatura*1.05;
        if((temperatura>=lbTemperatura&&temperatura<=upTemperatura)&&(umidita>=lbUmidita&&umidita<=upUmidita)){
            return true;
        }
        return false;
    }

    public void run() {
        oraCorrente=new Timestamp(System.currentTimeMillis());
        int ore= (int) (oraCorrente.getTime()/(1000*120)); //conversione da millisencondi in ore
        Double sommaUmidita=0d;
        Double sommaTemperatura=0d;
        Double mediaUmidita=0d;
        Double mediaTemperatura=0d;
        int c=0;
        if(ore>=ORA_INIZIALE &&ore<=ORA_FINALE) {
            try {
                ServerSocket serverSocket = new ServerSocket(tcpPort);
                Socket socket = serverSocket.accept();
                ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                StatoSensore stato=(StatoSensore) ois.readObject();
                link.put(stato.getIdSensore(), String.valueOf(socket.getInetAddress()));
                for(StatoSensore s : stati) {
                    sommaUmidita=0d;
                    mediaUmidita =0d;
                    sommaTemperatura=0d;
                    mediaTemperatura=0d;
                    c=0;
                    if(s.getIdSensore()==stato.getIdSensore()){
                        sommaUmidita+=s.getUmidita();
                    }
                    sommaTemperatura+=s.getTemperatura();
                    c++;
                }
                mediaUmidita =sommaUmidita/c;
                mediaTemperatura =sommaTemperatura/c;
                if(ControllaVariazione(mediaUmidita,mediaTemperatura,stato.getTemperatura(),stato.getUmidita())){
                    boolean messaggio=false;
                    ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(messaggio);
                    oos.close();
                }else{
                    if(!stati.isEmpty()){
                        np=stati.getLast().getNp();
                        np++;
                    }else{
                        np++;
                    }
                    stato.setNp(np);
                    stati.addLast(stato);
                    ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(np);
                    oos.close();
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            try{
                ServerSocket serverSocket = new ServerSocket(tcpPort);
                Socket socket = serverSocket.accept();
                boolean messaggio=false;
                ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(messaggio);
                oos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}