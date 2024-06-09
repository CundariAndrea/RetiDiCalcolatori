package Traccia1;

public class Struttura{
  private String nome;
  private String citta;
  private int nStelle; //da 1 a 5
  private int postiLetto;

  public Struttura(String nome,String citta,int nStelle, int postiLetto) throws IllegalArgumentException{
    if(postiLetto<=1||postiLetto>=5){
      throw new IllegalArgumentException("Il numero di posti letto " +
        "deve essere un numero tra 1 e 5");
    }
    this.citta=citta; this.nome=nome; this.nStelle=nStelle; this.postiLetto=postiLetto;
  }

  public int getPostiLetto() {
    return postiLetto;
  }

  public int getnStelle() {
    return nStelle;
  }

  public String getCitta() {
    return citta;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setCitta(String citta) {
    this.citta = citta;
  }

  public void setnStelle(int nStelle) {
    this.nStelle = nStelle;
  }

  public void setPostiLetto(int postiLetto) {
    this.postiLetto = postiLetto;
  }
}
