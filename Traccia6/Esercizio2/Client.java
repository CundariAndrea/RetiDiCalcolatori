package Traccia6.Esercizio2;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class Client {
    private static final Integer tcpPort=3000;
    private static final Integer udpPort=4000;
    private final InetAddress ia;

    public Client(InetAddress ia) {
        this.ia=ia;
    }

    public static void main(String[] args) {
        int i=1;
        Scanner scanner=new Scanner(System.in);
        String titolo;
        String testo;
        LinkedList<String> autori=new LinkedList<>();
        LinkedList<String> tag=new LinkedList<>();
        boolean ok=true;
        System.out.println("Inserisci il titolo della canzone");
        titolo=scanner.nextLine();
        System.out.println("Inserisci il testo della canzone");
        testo=scanner.nextLine();
        while(ok){
            System.out.println("Inserisci il "+i+"° autore della canzone");
            autori.add(scanner.nextLine());
            System.out.println("Vuoi inserire altri autori? (si|no)");
            if(scanner.nextLine().toLowerCase().equals("si")){
                ok=true;
                i++;
            }else{
                ok=false;
                i=1;
            }
        }
        ok=true;
        while(ok){
            System.out.println("Inserisci il "+i+"° tag della canzone");
            tag.add(scanner.nextLine());
            System.out.println("Vuoi inserire altri tag? (si|no)");
            if(scanner.nextLine().toLowerCase().equals("si")){
                ok=true;
                i++;
            }else{
                ok=false;
                i=1;
            }
        }
        try{
            ServerSocket serverSocket=new ServerSocket(Client.tcpPort);
            Socket socket=serverSocket.accept();
            Canzone c=new Canzone(titolo,testo,autori,tag);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(c);
            objectOutputStream.flush();
            objectOutputStream.close();
            socket.close();
            serverSocket.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}