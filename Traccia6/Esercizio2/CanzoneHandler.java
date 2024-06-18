package Traccia6.Esercizio2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.LinkedList;

public class CanzoneHandler extends Thread{
    private LinkedList<Canzone> canzoni;
    private final Integer tcpInput;
    private final Integer oraInizio=8;
    private final Integer oraFine=13;
    private Timestamp currentTime;
    private Integer idCanzone;

    public CanzoneHandler(Integer tcpInput, Integer idCanzone,LinkedList<Canzone> canzoni) {
        this.canzoni = canzoni;
        this.tcpInput = tcpInput;
        this.idCanzone = idCanzone;
    }

    private LinkedList<String> EstraiBlocchi(Canzone c){
        LinkedList<String> blocchi=new LinkedList<>();
        String testo=c.getTesto();
        for(int i=0;i<testo.length()/50;i++){
            blocchi.add(testo.substring((50*i),(50*(i+1))));
        }
        return blocchi;
    }

    private boolean VerificaCanzone(Canzone canzone) {
        boolean ris=true;
        String titolo = canzone.getTitolo();
        for(String autore:canzone.getAutori()){
            for(Canzone c:canzoni){
                if(c.getTitolo().equals(titolo) && c.getAutori().contains(autore)){
                    ris=false;
                }
            }
        }
        LinkedList<String> blocchi=EstraiBlocchi(canzone);
        for(Canzone c:canzoni){
            for(String blocco: EstraiBlocchi(c)){
                if(blocchi.contains(blocco)){
                    ris=false;
                }
            }
        }
        return ris;
    }


    public void run(){
        try{
            currentTime=new Timestamp(System.currentTimeMillis());
            ServerSocket serverSocket=new ServerSocket(tcpInput);
            Socket socket=serverSocket.accept();
            if(currentTime.getTime()/(1000*120)>oraInizio && currentTime.getTime()/(1000*120)<oraFine){
                ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
                Canzone canzone=(Canzone)in.readObject();
                if(VerificaCanzone(canzone)){
                    idCanzone++;
                    canzone.setId(idCanzone);
                    ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(idCanzone);
                    out.close();
                }else{
                    ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject("Canzone non valida riprovare");
                    out.close();
                }
                in.close();
                socket.close();
                serverSocket.close();
            }else{
                ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
                out.writeObject("Orario non rispettato, riprovare dalle 8 alle 13");
                out.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
