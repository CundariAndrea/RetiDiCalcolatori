package Traccia2;

import java.io.Serializable;


public class Libreria implements Serializable {
    private String partitaIva;
    private String nome;
    private String citta;

    public Libreria(String partitaIva, String nome, String citta){
        this.partitaIva = partitaIva;
        this.nome = nome;
        this.citta = citta;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }
}
