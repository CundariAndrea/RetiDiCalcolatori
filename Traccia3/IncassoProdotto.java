package Traccia3;

import java.io.Serializable;

public class IncassoProdotto implements Serializable {
    private String nomeVino;
    private Integer quantita;
    private Double importo;

    public IncassoProdotto(String nomeVino, Integer quantita, Double importo) {
        this.nomeVino = nomeVino;
        this.quantita = quantita;
        this.importo = importo;
    }

    public String getNomeVino() {
        return nomeVino;
    }

    public void setNomeVino(String nomeVino) {
        this.nomeVino = nomeVino;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Double getImporto() {
        return importo;
    }

    public void setImporto(Double importo) {
        this.importo = importo;
    }

}
