package Traccia7.Esercizio2;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.LinkedList;

public class EsameClinico{
    private String codice;
    private Medico medico1;
    private Medico medico2;
    private HashMap<Medico, LinkedList<InetAddress>> prenotati;
    private HashMap<InetAddress, Integer> numeriPrenotazione;

    public EsameClinico(String codice,String medico1,String medico2){
        this.codice = codice;
        prenotati = new HashMap<>();
        LinkedList<InetAddress> lista1 = new LinkedList<>();
        LinkedList<InetAddress> lista2 = new LinkedList<>();
        this.medico1 = new Medico(medico1);
        this.medico2 = new Medico(medico2);
        prenotati.put(this.medico1,lista1);
        prenotati.put(this.medico2,lista2);
        numeriPrenotazione = new HashMap<>();
    }

    public String getCodice(){
        return codice;
    }

    public Medico getMedico1() {
        return medico1;
    }

    public Medico getMedico2() {
        return medico2;
    }

    public HashMap<Medico, LinkedList<InetAddress>> getPrenotati(){
        return prenotati;
    }

    public synchronized Integer addPrenotazione(InetAddress ip){
        Integer ris = 0;
        for(InetAddress i:numeriPrenotazione.keySet()){
            ris=numeriPrenotazione.get(i);
        }
        ris++;
        numeriPrenotazione.put(ip,ris);
        return ris+1;
    }

    public synchronized Integer getPrenotazione(InetAddress ip){
        return numeriPrenotazione.get(ip);
    }
}