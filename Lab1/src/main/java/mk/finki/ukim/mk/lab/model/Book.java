package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String isbn;
    public String title;
    public String genre;
    public int year;
    @ManyToMany
    public List<Author> authors;
    @ManyToOne
    private BookStore bookStore;
    @ManyToOne
    private BookPrice bookPrice;

    public Book() {
    }

    public Book(BookStore bookStore, String isbn, String title, String genre, int year, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        this.bookStore = bookStore;
    }

    public Book(BookStore bookStore,String isbn, String title, String genre, int year) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = new ArrayList<>();
        this.bookStore = bookStore;
    }

    public Book(String isbn, String title, String genre, int year, BookStore bookStore, BookPrice bookPrice) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.bookStore = bookStore;
        this.bookPrice = bookPrice;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }
}
