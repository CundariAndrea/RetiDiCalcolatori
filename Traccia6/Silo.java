package Traccia6;

import java.io.Serializable;

public class Silo implements Serializable {
    private Integer id;
    private String cereale;
    private Integer qCereali;
    private String regione;
    public Silo(Integer id, String tipo, Integer qCereali,String regione) {
        this.id = id;
        this.cereale = tipo;
        this.qCereali = qCereali;
        this.regione = regione;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCereale() {
        return cereale;
    }

    public void setCereale(String cereale) {
        this.cereale = cereale;
    }

    public Integer getqCereali() {
        return qCereali;
    }

    public void setqCereali(Integer qCereali) {
        this.qCereali = qCereali;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }
}
