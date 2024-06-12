package Traccia5;

import java.io.Serializable;

import java.util.Collections;
import java.util.LinkedList;

public class BookList implements Serializable {
    private LinkedList<Book> books;

    public BookList() {
        books= (LinkedList<Book>) Collections.synchronizedList(books);
    }

    public LinkedList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
}
