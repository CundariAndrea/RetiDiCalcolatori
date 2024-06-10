package Traccia4;

public class Grossista {
    private String pIva;
    private String provincia;

    public Grossista(String pIva, String provincia) {
        this.pIva = pIva;
        this.provincia = provincia;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
