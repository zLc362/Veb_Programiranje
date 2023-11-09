package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class BookRepository {
    public List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
        this.books.add(new Book("1984","Nineteen Eighty-Four","Sci-Fi",1949));
        this.books.add(new Book("69","Moby-Dick","Adventure",1851));
        this.books.add(new Book("420","Animal Farm","Fiction",1945));
        this.books.add(new Book("256","Frankenstein","Horror",1818));
        this.books.add(new Book("512","The Great Gatsby","Tragedy",1925));
    }

    public List<Book> findAll() {
        return books;
    }
    public Book findByIsbn(String isbn){
        return books.stream().filter(x->x.isbn.equals(isbn)).findFirst().orElse(null);
    }
    public Author addAuthorToBook(Author author, Book book){
        books.get(books.indexOf(book)).addAuthor(author);
        return author;
    }
}
