package Traccia3;

import java.util.HashMap;
import java.util.LinkedList;

public class AziendaService {
    private HashMap<Integer, LinkedList<IncassoProdotto>> dati;

    public AziendaService() {

    }

    public void Vendita(Integer idAzienda,String nomeVino,Integer quantita,Double incasso) {
        if(dati.containsKey(idAzienda)){
            LinkedList<IncassoProdotto> lista = dati.get(idAzienda);
            IncassoProdotto tmp=new IncassoProdotto(nomeVino,quantita,incasso);
            lista.add(tmp);
            dati.put(idAzienda, lista);
        }
        IncassoProdotto tmp=new IncassoProdotto(nomeVino,quantita,incasso);
        LinkedList<IncassoProdotto> lista=new LinkedList<>();
        lista.add(tmp);
        dati.put(idAzienda, lista);
    }

    public IncassoProdotto MaggioreIncasso(Integer idAzienda){
        LinkedList<IncassoProdotto> lista = dati.get(idAzienda);
        IncassoProdotto incasso=null;
        Double max=(double)-1;
        for(IncassoProdotto tmp:lista){
            if(tmp.getImporto()>max){
                max=tmp.getImporto();
                incasso=tmp;
            }
        }
        return incasso;
    }
}
