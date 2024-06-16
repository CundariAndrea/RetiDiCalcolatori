package Traccia2020;

public class Offerta {
    String hostnameCentroBenessere;
    Double prezzo;

    public Offerta(String hostnameCentroBenessere, Double prezzo) {
        this.hostnameCentroBenessere = hostnameCentroBenessere;
        this.prezzo = prezzo;
    }

    public String getHostnameCentroBenessere() {
        return hostnameCentroBenessere;
    }

    public void setHostnameCentroBenessere(String hostnameCentroBenessere) {
        this.hostnameCentroBenessere = hostnameCentroBenessere;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }
}
