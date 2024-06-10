package Traccia4;

import java.util.HashMap;
import java.util.LinkedList;

public class OrtaggiService {
    private HashMap <Grossista, LinkedList<PrezzoProdotto>> dati;


    public OrtaggiService(HashMap <Grossista, LinkedList<PrezzoProdotto>> dati) {
        this.dati = dati;
    }

    public void updatePrezzo(PrezzoProdotto p) {
        String grossista=p.getpIva();
        for(Grossista g: dati.keySet()){
            if(g.getpIva().equals(grossista)){
                LinkedList<PrezzoProdotto> prezzo=dati.get(g);
                for(PrezzoProdotto pp : prezzo){
                    if(pp.getNomeOrtaggio().equals(p.getNomeOrtaggio())){
                        pp.setPrezzo(p.getPrezzo());
                    }
                }
            }
        }
    }

    public String MinPrezzoMedio(String ortaggio){
        Double prezzoTotale=0d;
        Double prezzoMedio=0d;
        Double prezzoMin=-1d;
        int c=0;
        String ris="";
        HashMap<String,LinkedList<PrezzoProdotto>> prezzi=new HashMap<>();
        for(Grossista g: dati.keySet()){
            String provincia=g.getProvincia();
            if(prezzi.containsKey(provincia)){
                for(PrezzoProdotto p: dati.get(g)){
                    if(p.getNomeOrtaggio().equals(ortaggio)){
                        LinkedList<PrezzoProdotto> tmp=prezzi.get(provincia);
                        tmp.addLast(p);
                        prezzi.put(provincia,tmp);
                    }
                }
            }else{
                LinkedList<PrezzoProdotto> tmp=new LinkedList<>();
                for(PrezzoProdotto p:dati.get(g)){
                    if(p.getNomeOrtaggio().equals(ortaggio)){
                        tmp.addLast(p);
                        prezzi.put(provincia,tmp);
                    }
                }
            }
        }
        for(String s: prezzi.keySet()){
            c=0;
            for(PrezzoProdotto p: prezzi.get(s)){
                prezzoTotale+=p.getPrezzo();
                c++;
            }
            prezzoMedio=prezzoTotale/c;
            if(prezzoMedio<prezzoMin){
                prezzoMin=prezzoMedio;
                ris=s;
            }
        }
        return ris;
    }
}
