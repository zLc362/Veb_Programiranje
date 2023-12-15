package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class InMemoryBookRepository {

    public List<Book> findAll() {
        return DataHolder.books;
    }
    public Optional<Book> findByIsbn(String isbn){
        return DataHolder.books.stream().filter(x->x.isbn.equals(isbn)).findFirst();
    }
    public Optional<Book> findById(Long id){
        return DataHolder.books.stream().filter(x->x.getId().equals(id)).findFirst();
    }
    public Author addAuthorToBook(Author author, Book book){
        DataHolder.books.get(DataHolder.books.indexOf(book)).addAuthor(author);
        return author;
    }
    public Optional<Book> addBook(String isbn, String title, String genre, Integer year, Long bookStoreId){
        BookStore store = DataHolder.bookStores.stream().filter(x->x.getId().equals(bookStoreId)).findFirst().orElse(null);
        DataHolder.books.removeIf(x->x.isbn.equals(isbn));
        Book book = new Book(store,isbn,title,genre,year);
        DataHolder.books.add(book);
        return Optional.of(book);
    }

    public void deleteById(Long id) {
        DataHolder.books.removeIf(book -> book.getId().equals(id));
    }
}
