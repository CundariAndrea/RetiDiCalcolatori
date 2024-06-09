package Traccia1;
import java.util.*;

public class StrutturaOperatorService{
  private HashMap<Struttura,Integer> dati=null;
  //il valore int sarebbe il numero di posti attualmente prenotati

  private Integer numeroPersone (String nome){
    for(Struttura s :dati.keySet()){
      if (s.getNome().equals(nome)) {
        return dati.get(s);
      }
    }
    return -1;
  }

  private Struttura miglioreStruttura(String citta, int stelle){
    Struttura tmp=null;
    int postiLiberi=Integer.MAX_VALUE;
    int temp=0;
    for (Struttura s: dati.keySet() ){
      if(s.getCitta().equals(citta) && s.getnStelle()==stelle){
        temp=s.getPostiLetto()-dati.get(s);
        if(temp!=0&&temp<=postiLiberi){
          tmp=s;
          postiLiberi=temp;
        }
      }
    }
    return tmp;
  }
}
