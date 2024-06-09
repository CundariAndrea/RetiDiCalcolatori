package Traccia2;

import java.util.HashMap;
import java.util.LinkedList;

public class LibrerieService {
    private HashMap<Libreria,HashMap<Libro,Integer>> dati;

    public int VenditeISBN(String partitaIva,String ISBN){
        for(Libreria lib : dati.keySet()){
            if(lib.getPartitaIva().equals(partitaIva)){
                HashMap<Libro,Integer> tmp=dati.get(lib);
                for(Libro libro : tmp.keySet()){
                    if(libro.getISBN().equals(ISBN)){
                        return tmp.get(libro);
                    }
                }
            }
        }
        return -1;
    }

    public Libreria venditeCategoria(String categoria){
        HashMap<Libro,Integer> tmp;
        int max=-1;
        Libreria ris=null;
        for(Libreria lib : dati.keySet()){
            tmp=dati.get(lib);
            for(Libro libro : tmp.keySet()){
                if(libro.getCategoria().equals(categoria)){
                    if(max<tmp.get(libro)){
                        max=tmp.get(libro);
                        ris=lib;
                    }
                }
            }
        }
        return ris;
    }
}
