package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {
    public final BookRepository bookRepository;
    public final AuthorRepository authorRepository;

    public BookServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElse(null);
        Author author = authorRepository.findById(authorId).orElse(null);
        bookRepository.addAuthorToBook(author,book);
        return author;
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    public void save(String isbn, String title, String genre, Integer year, Long bookStoreId){
        bookRepository.addBook(isbn,title,genre,year,bookStoreId);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}
