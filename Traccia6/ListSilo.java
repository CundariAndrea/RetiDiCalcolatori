package Traccia6;

import java.io.Serializable;
import java.util.LinkedList;

public class ListSilo implements Serializable {
    LinkedList<Silo> silo;

    public ListSilo() {
        silo = new LinkedList<>();
    }

    public void addSilo(Silo silo) {
        this.silo.addLast(silo);
    }

    public Silo removeSilo() {
        return this.silo.removeLast();
    }

    public LinkedList<Silo> getSilo() {
        return silo;
    }
}
