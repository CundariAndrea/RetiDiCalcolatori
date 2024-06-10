package Traccia4;

import java.io.Serializable;

public class PrezzoProdotto implements Serializable {
    private String pIva;
    private String nomeOrtaggio;
    private Double prezzo;

    public PrezzoProdotto(String pIva, String nomeOrtaggio, Double prezzo) {
        this.pIva = pIva;
        this.nomeOrtaggio = nomeOrtaggio;
        this.prezzo = prezzo;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }

    public String getNomeOrtaggio() {
        return nomeOrtaggio;
    }

    public void setNomeOrtaggio(String nomeOrtaggio) {
        this.nomeOrtaggio = nomeOrtaggio;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }
}
