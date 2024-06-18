package Traccia7;

import java.io.Serializable;
import java.util.LinkedList;

public class ListaProdotti implements Serializable {
    LinkedList<Prodotto> list;

    public ListaProdotti() {
        list = new LinkedList<>();
    }

    public void add(Prodotto p) {
        list.add(p);
    }
    public Prodotto getProdotto() {
        return list.getLast();
    }
    public LinkedList<Prodotto> getList() {
        return list;
    }
}
