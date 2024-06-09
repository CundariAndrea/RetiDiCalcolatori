package Traccia2.Esercizio2;
import java.io.Serializable;
import java.sql.Timestamp;

public class Concorso implements Serializable {
    private int id;
    private int numeroPosti;
    private Timestamp dataScadenza;

    public Concorso(int id, int numeroPosti, Timestamp dataScadenza) {
        this.id = id;
        this.numeroPosti = numeroPosti;
        this.dataScadenza = dataScadenza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    public Timestamp getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Timestamp dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

}
