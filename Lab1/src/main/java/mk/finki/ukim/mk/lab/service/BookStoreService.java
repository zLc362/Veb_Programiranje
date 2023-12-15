package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;

import java.util.List;
import java.util.Optional;

public interface BookStoreService {
    List<BookStore> findAll();
    Optional<BookStore> findById(Long id);

    Optional<BookStore> save(Long id,String name, String adress, String city);

    void deleteById(Long id);
    Double priceWithDiscount(Long id);
    List<Book> getAllBooks(Long id);
}
