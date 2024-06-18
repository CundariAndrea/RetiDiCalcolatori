package Traccia7.Esercizio2;

import java.util.LinkedList;

public class Server {
    private final Integer inputTCP=3000;


    private LinkedList<EsameClinico> esami;

    public Server() {
        esami = new LinkedList<>();
    }

    public static void main(String[] args) {
        Server server = new Server();
        PrenotazioneHandler ph=new PrenotazioneHandler(server.esami,server.inputTCP);
        ph.start();
    }
}
