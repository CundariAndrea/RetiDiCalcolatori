package Traccia5.Esercizio2;

import java.io.Serializable;

public class StatoSensore implements Serializable {
    private Integer idSensore;
    private Integer np;
    private Double temperatura;
    private Double umidita;

    public StatoSensore(Integer idSensore, Integer np, Double temperatura, Double umidita) {
        this.idSensore = idSensore;
        this.np = np;
        this.temperatura = temperatura;
        this.umidita = umidita;
    }

    public Integer getIdSensore() {
        return idSensore;
    }

    public void setIdSensore(Integer idSensore) {
        this.idSensore = idSensore;
    }

    public Integer getNp() {
        return np;
    }

    public void setNp(Integer np) {
        this.np = np;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getUmidita() {
        return umidita;
    }

    public void setUmidita(Double umidita) {
        this.umidita = umidita;
    }
}
