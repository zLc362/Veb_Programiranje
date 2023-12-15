package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookPrice;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    public final BookStoreRepository bookStoreRepository;
    public final BookRepository bookRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository, BookRepository bookRepository) {
        this.bookStoreRepository = bookStoreRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public Optional<BookStore> findById(Long id) {
        return bookStoreRepository.findById(id);
    }

    @Override
    public Optional<BookStore> save(Long id,String name, String adress, String city) {
        return Optional.of(bookStoreRepository.save(new BookStore(name,adress,city)));
    }

    @Override
    public void deleteById(Long id) {
        bookStoreRepository.deleteById(id);
    }

    @Override
    public Double priceWithDiscount(Long id) {
        BookStore bookStore = bookStoreRepository.findById(id).orElseThrow();
        List<BookPrice> bookPrices = bookRepository.findAll().stream()
                .filter(x->x.getBookStore().getId().equals(bookStore.getId())).map(Book::getBookPrice).toList();
        Double sum = bookPrices.stream().mapToDouble(x->x.getPrice() - x.getPrice()*x.getDiscount()/100).sum();
        return sum*bookStore.getNumOfCopiesSold();
    }

    @Override
    public List<Book> getAllBooks(Long id) {
        return bookRepository.findAll().stream().filter(x->x.getBookStore().getId().equals(id)).toList();
    }
}
