package Traccia6.Esercizio2;

import java.io.Serializable;
import java.util.LinkedList;

public class Richiesta implements Serializable {
    private LinkedList<String> paroleChiave;
    private String nomeAutore;

    public Richiesta(String nomeAutore,LinkedList<String> paroleChiave) {
        this.nomeAutore = nomeAutore;
        this.paroleChiave = paroleChiave;
    }

    public LinkedList<String> getParoleChiave() {
        return paroleChiave;
    }

    public String getNomeAutore() {
        return nomeAutore;
    }
}
