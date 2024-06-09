package Traccia3.Esercizio2;

public class Asta {
    private Integer idAsta;
    private String nomeProdotto;

    public Asta(Integer idAsta, String nomeProdotto) {
        this.idAsta = idAsta;
        this.nomeProdotto = nomeProdotto;
    }

    public Integer getIdAsta() {
        return idAsta;
    }

    public void setIdAsta(Integer idAsta) {
        this.idAsta = idAsta;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

}
