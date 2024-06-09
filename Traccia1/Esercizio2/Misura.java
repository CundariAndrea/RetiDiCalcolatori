package Traccia1.Esercizio2;

import java.io.Serializable;
import java.sql.Timestamp;

public class Misura implements Serializable {
    private int idSensore;
    private double valoreMisurato;
    private Timestamp tempo=null;

    public Misura(int idSensore,double valoreMisurato,Object tempo){
        this.idSensore=idSensore;
        this.valoreMisurato=valoreMisurato;
        this.tempo=(Timestamp) tempo;
    }
    public int getIdSensore() {
        return idSensore;
    }

    public void setIdSensore(int idSensore) {
        this.idSensore = idSensore;
    }

    public double getValoreMisurato() {
        return valoreMisurato;
    }

    public void setValoreMisurato(double valoreMisurato) {
        this.valoreMisurato = valoreMisurato;
    }

    public Timestamp getTempo() {
        return tempo;
    }

    public void setTempo(Timestamp tempo) {
        this.tempo = tempo;
    }
}
