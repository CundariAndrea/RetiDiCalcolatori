package Traccia2020;

import java.util.Date;

public class Richiesta {
    Date data;
    int numeroPersone;

    public Richiesta(Date data, int numeroPersone) {
        this.data = data;
        this.numeroPersone = numeroPersone;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(int numeroPersone) {
        this.numeroPersone = numeroPersone;
    }
}
