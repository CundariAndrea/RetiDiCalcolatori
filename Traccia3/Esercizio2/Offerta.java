package Traccia3.Esercizio2;

import java.io.Serializable;

public class Offerta implements Serializable {
    private String cf;
    private Integer idAsta;
    private Double importo; //in euro

    public Offerta(String cf, Integer idAsta, Double importo) {
        this.cf = cf;
        this.idAsta = idAsta;
        this.importo = importo;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public Integer getIdAsta() {
        return idAsta;
    }

    public void setIdAsta(Integer idAsta) {
        this.idAsta = idAsta;
    }

    public Double getImporto() {
        return importo;
    }

    public void setImporto(Double importo) {
        this.importo = importo;
    }

}
