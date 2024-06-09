package Traccia2;

import java.io.Serializable;

public class Libro implements Serializable {
    private String ISBN;
    private String categoria;
    private String nome;

    public Libro(String ISBN, String categoria, String nome) {
        this.ISBN = ISBN;
        this.categoria = categoria;
        this.nome = nome;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
