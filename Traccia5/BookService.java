package Traccia5;

import java.util.StringTokenizer;

public class BookService {
    private BookList books;

    public BookService(BookList books) {
        this.books = books;
    }

    public BookList SearchBook(String input){
        StringTokenizer st = new StringTokenizer(input,"-");
        String autore = st.nextToken();
        String genere = st.nextToken();
        float prezzo = Float.parseFloat(st.nextToken());
        BookList ris = new BookList();
        for(Book b: books.getBooks()){
            if(b.getAuthor().equals(autore)&&b.getGenere().equals(genere)&&b.getPrice()<=prezzo){
                ris.addBook(b);
            }
        }
        return ris;
    }

    public boolean AddBook(Book b){
        int c=0;
        boolean ris=true;
        for(Book bb: books.getBooks()){
            if(bb.getAuthor().equals(b.getAuthor())){
                c++;
                if(bb.getTitle().equals(b.getTitle())){
                    ris=false;
                }
            }
        }
        if(!ris || c>=10){
            return false;
        }else{
            books.addBook(b);
            return true;
        }
    }
}
