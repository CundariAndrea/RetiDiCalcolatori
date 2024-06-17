package Traccia6;

public class SilosService {
    private ListSilo lista;

    public SilosService() {
        this.lista = new ListSilo();
    }

    public ListSilo QuantitaCereale(String nome,int quantita){
        ListSilo ris=new ListSilo();
        for(Silo s: lista.getSilo()){
            if(s.getCereale().equals(nome)&&s.getqCereali()>=quantita){
                ris.addSilo(s);
            }
        }
        return ris;
    }

    public ListSilo MaxSilos(String regione){
        ListSilo ris=new ListSilo();
        int max=-1;
        Silo tmp;
        String cereale;
        for(Silo s: lista.getSilo()){
            max=-1;
            if(s.getRegione().equals(regione)){
                tmp=s;
                cereale=s.getCereale();
                max=s.getqCereali();
                for(Silo s1: lista.getSilo()){
                    if(s1.getRegione().equals(regione) && s1.getCereale().equals(cereale) && s1.getqCereali()>max){
                        max=s1.getqCereali();
                        tmp=s1;
                    }
                }
                ris.addSilo(tmp);
            }
        }
        return ris;
    }
}