package Traccia5;

import java.io.Serializable;

public class Book implements Serializable {
    private String ISBN;
    private String title;
    private String author;
    private String genere;
    private float price;

    public Book(String ISBN, String title, String author, String genere, float price) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.genere = genere;
        this.price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
