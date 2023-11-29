package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Optional<Book> findBookByIsbn(String isbn);


    void save(String isbn, String title, String genre, Integer year, Long bookStoreId);

    void deleteById(Long id);
    Optional<Book> findById(Long id);
}
