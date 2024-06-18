package Traccia7;

import java.io.Serializable;

public class Prodotto implements Serializable {
    private String codice;
    private String nome;
    private String produttore;
    private Double prezzo;

    public Prodotto(String codice, String nome, String produttore, Double prezzo) {
        this.codice = codice;
        this.nome = nome;
        this.produttore = produttore;
        this.prezzo = prezzo;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProduttore() {
        return produttore;
    }

    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }
}
