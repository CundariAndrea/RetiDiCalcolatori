package Traccia6.Esercizio2;

import java.io.Serializable;
import java.util.LinkedList;

public class Canzone implements Serializable {
    private Integer id;
    private String titolo;
    private String testo;
    private LinkedList<String> autori;
    private LinkedList<String> tag;

    public Canzone(Integer id, String titolo, String testo, LinkedList<String> autori, LinkedList<String> tag) {
        this.id = id;
        this.titolo = titolo;
        this.testo = testo;
        this.autori = autori;
        this.tag = tag;
    }

    public Canzone(String titolo, String testo, LinkedList<String> autori, LinkedList<String> tag) {
        this.titolo = titolo;
        this.testo = testo;
        this.autori = autori;
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public LinkedList<String> getAutori() {
        return autori;
    }

    public void setAutori(LinkedList<String> autori) {
        this.autori = autori;
    }

    public LinkedList<String> getTag() {
        return tag;
    }

    public void setTag(LinkedList<String> tag) {
        this.tag = tag;
    }
}
