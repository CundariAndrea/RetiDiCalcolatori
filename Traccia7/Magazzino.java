package Traccia7;

import java.util.HashMap;

public class Magazzino {
    private String codice;
    private HashMap<Prodotto,Integer> vendite;
    /* indica quante unita di ogni prodotto sono state vendute in
    questo magazzino, si precisa che l'oggetto prodotto al suo interno ha un valore produttore,
    è facile intuire quindi che ci si riferisce alle unita di prodotto x
    che il produttore y ha venduto nel magazzino k */

    public Magazzino(String codice, HashMap<Prodotto,Integer> vendite) {
        this.codice = codice;
        this.vendite = vendite;
    }
    public String getCodice() {
        return codice;
    }
    public void setCodice(String codice) {
        this.codice = codice;
    }
    public HashMap<Prodotto,Integer> getVendite() {
        return vendite;
    }
    public void setVendite(HashMap<Prodotto,Integer> vendite) {
        this.vendite = vendite;
    }
}
