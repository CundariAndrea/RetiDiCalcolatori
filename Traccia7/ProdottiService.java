package Traccia7;

import java.util.HashMap;

public class ProdottiService {
    private HashMap<Magazzino,ListaProdotti> dati;


    public Prodotto ProdottoPiuVenduto(String produttore){
        Prodotto ris=null;
        int max=-1;
        for(Magazzino m: dati.keySet()){
            ListaProdotti li=dati.get(m);
            for(Prodotto p:li.getList()){
                if(p.getProduttore().equals(produttore)&&m.getVendite().get(p)>max){
                    max=m.getVendite().get(p);
                    ris=p;
                }
            }
        }
        return ris;
    }

    private void TrovaMassimi(ListaProdotti ris,HashMap<Prodotto,Double> guadagno){
        Double max=-1d;
        Prodotto tmp=null;
        for(int i=0;i<3;i++){
            max=-1d;
            for(Prodotto p:guadagno.keySet()){
                if(guadagno.get(p)>max){
                    max=guadagno.get(p);
                    tmp=p;
                }
            }
            guadagno.remove(tmp);
            ris.add(tmp);
        }
    }

    public ListaProdotti ProdottoMaxIncasso(String idMagazzino){
        ListaProdotti ris=null;
        int max=-1;
        double tot =0d;
        HashMap<Prodotto,Double> guadagno=new HashMap<>();
        for(Magazzino m: dati.keySet()){
            if(m.getCodice().equals(idMagazzino)){
                ListaProdotti li=dati.get(m);
                for(Prodotto p:li.getList()){
                    HashMap<Prodotto,Integer> vendite=m.getVendite();
                    tot =p.getPrezzo()*vendite.get(p);
                    guadagno.put(p, tot);
                }
            }
        }
        TrovaMassimi(ris,guadagno);
        return ris;
    }
}
