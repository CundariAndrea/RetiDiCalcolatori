package Traccia7.Esercizio2;

import java.io.Serializable;

public class Medico implements Serializable{
    private String matricola;

    public Medico(String matricola) {
        this.matricola = matricola;
    }
    public String getMatricola() {
        return matricola;
    }
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }
}
