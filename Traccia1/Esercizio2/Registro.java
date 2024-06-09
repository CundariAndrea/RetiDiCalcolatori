package Traccia1.Esercizio2;

import java.util.HashMap;
import java.util.LinkedList;

public class Registro {
    private HashMap<Integer, LinkedList<Misura>> dati=null;
    private LinkedList<Misura> tmp;
    public Registro(){
        this.dati=new HashMap<Integer,LinkedList<Misura>>();
    }

    public Misura Trova(int id){
        tmp=dati.get(id);
        return  tmp.getLast();
    }

    public synchronized void add(Integer id, Misura m){
        if(dati.get(id)==null){
         tmp=new LinkedList<Misura>();
         tmp.addLast(m);
         dati.put(id,tmp);
        }else{
            tmp=dati.get(id);
            tmp.addLast(m);
            dati.put(id,tmp);
        }
    }

    public HashMap<Integer,LinkedList<Misura>> getMap(){
        return dati;
    }
}
