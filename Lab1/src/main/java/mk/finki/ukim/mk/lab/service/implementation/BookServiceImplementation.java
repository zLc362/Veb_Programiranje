package mk.finki.ukim.mk.lab.service.implementation;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {
    public final BookRepository bookRepository;
    public final AuthorRepository authorRepository;
    public final BookStoreRepository bookStoreRepository;

    public BookServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository, BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
    }
    public void initializeAllTables(){
        authorRepository.save(new Author("Stephen","King","Stephen Edwin King is an American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels. Described as the \"King of Horror\", his books have sold more than 350 million copies as of 2006, and many have been adapted into films, television series, miniseries, and comic books."));
        authorRepository.save(new Author("George","Orwell","Eric Arthur Blair, better known by his pen name George Orwell, was an English novelist, essayist, journalist, and critic. His work is characterised by lucid prose, social criticism, opposition to totalitarianism, and support of democratic socialism."));
        authorRepository.save(new Author("Lewis","Carrol","Charles Lutwidge Dodgson, better known by his pen name Lewis Carroll, was an English author, poet, mathematician and photographer. His most notable works are Alice's Adventures in Wonderland and its sequel Through the Looking-Glass. He was noted for his facility with word play, logic, and fantasy."));
        authorRepository.save(new Author("Roald","Dahl","Roald Dahl was a British author of popular children's literature and short stories, a poet, screenwriter and a wartime fighter ace. His books have sold more than 300 million copies worldwide. Dahl has been called \"one of the greatest storytellers for children of the 20th century\"."));
        authorRepository.save(new Author("James","Patterson","James Brendan Patterson is an American author. Among his works are the Alex Cross, Michael Bennett, Women's Murder Club, Maximum Ride, Daniel X, NYPD Red, Witch & Wizard, Private and Middle School series, as well as many stand-alone thrillers, non-fiction, and romance novels."));

        bookStoreRepository.save(new BookStore("Bubamara","Negotino","Kire krstev 50"));
        bookStoreRepository.save(new BookStore("Zivjanka","Stip","Andon Panev 20"));
        bookStoreRepository.save(new BookStore("Antigona","Skopje","11ti Oktomvri 13"));
        bookStoreRepository.save(new BookStore("Bubi","Kavadarci","8mi Noemvri 88"));
        bookStoreRepository.save(new BookStore("Liberta","Struga","Ilindenska 14"));

        BookStore bookStore = bookStoreRepository.findAll().get(0);

        bookRepository.save(new Book(bookStore,"1984","Nineteen Eighty-Four","Sci-Fi",1949));
        bookRepository.save(new Book(bookStore,"84","Moby-Dick","Adventure",1851));
        bookRepository.save(new Book(bookStore,"420","Animal Farm","Fiction",1945));
        bookRepository.save(new Book(bookStore,"256","Frankenstein","Horror",1818));
        bookRepository.save(new Book(bookStore,"512","The Great Gatsby","Tragedy",1925));
    }
    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Author addAuthorToBook(Long authorId, String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElse(null);
        Author author = authorRepository.findById(authorId).orElse(null);
        if (book!=null) {
            book.addAuthor(author);
            bookRepository.save(book);
        }
        return author;
    }

    @Override
    @Transactional
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    public void save(Long id,String isbn, String title, String genre, Integer year, Long bookStoreId){
        BookStore store = bookStoreRepository.findById(bookStoreId).orElse(null);
        Book book = bookRepository.findById(id).orElse(null);
        if (year!=null && store!=null && isbn!=null) {
            if (book!=null) {
                book.setBookStore(store);
                book.setGenre(genre);
                book.setTitle(title);
                book.setIsbn(isbn);
                book.setYear(year);
                bookRepository.save(book);
            }else{
                bookRepository.save(new Book(store, isbn, title, genre, year));
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Double getPriceWithDiscount(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return book.getBookPrice().getPrice()*book.getBookPrice().getDiscount()/100;
    }
}
