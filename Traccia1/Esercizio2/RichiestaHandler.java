package Traccia1.Esercizio2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RichiestaHandler extends Thread{
    private Socket s;
    private Registro r;
    public RichiestaHandler(Socket s,Registro r){
        this.s=s;
        this.r=r;
    }

    public void run(){
        try{
            ObjectInputStream in=new ObjectInputStream(s.getInputStream());
            ObjectOutputStream out= new ObjectOutputStream(s.getOutputStream());
            String id=(String) in.readObject();
            Misura m=r.Trova(Integer.parseInt(id));
            out.writeObject(m);
            in.close();
            out.close();
        }catch (IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }
}
