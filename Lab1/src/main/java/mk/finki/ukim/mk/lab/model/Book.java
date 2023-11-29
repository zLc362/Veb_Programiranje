package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Data
public class Book {
    private Long id;
    public String isbn;
    public String title;
    public String genre;
    public int year;
    public List<Author> authors;
    private BookStore bookStore;

    public Book(BookStore bookStore, Long id,String isbn, String title, String genre, int year, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        this.id = id;
        this.bookStore = bookStore;
    }

    public Book(BookStore bookStore,Long id,String isbn, String title, String genre, int year) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = new ArrayList<>();
        this.id = id;
        this.bookStore = bookStore;
    }
    public Book(BookStore bookStore,String isbn, String title, String genre, int year) {
        Random rnd = new Random();
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = new ArrayList<>();
        this.id = rnd.nextLong(1000,10000);
        this.bookStore = bookStore;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }
}
