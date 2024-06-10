package Traccia3.Esercizio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Timestamp;
import java.util.*;

public class Server {
    private LinkedList<Asta> aste;
    private HashMap<Asta, LinkedList<Offerta>> offerte;
    private HashMap<Asta, Timestamp> asteAttive;
    private LinkedList<Asta> asteTerminate;
    private final Integer udpPort = 4000;

    public Server() {
        aste= (LinkedList<Asta>) Collections.synchronizedList(new LinkedList<Asta>());
        asteAttive = new HashMap<>();
        asteTerminate = new LinkedList<>();
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            while (true) {
                for (Asta a : server.aste) {
                    Random random = new Random();
                    Integer portaTcp = random.nextInt((40_000 - 30_000) + 1) + 30_000;
                    AsteHandler ah = new AsteHandler(portaTcp, a, server.asteAttive, server.asteTerminate);
                    ah.start();
                    OfferteHandler of = new OfferteHandler(portaTcp, server.offerte, server.asteAttive);
                    of.start();
                }
                if (!server.asteTerminate.isEmpty()) {
                    AsteTerminateHandler ath = new AsteTerminateHandler(server.udpPort,server.asteTerminate,server.offerte);
                    ath.start();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}