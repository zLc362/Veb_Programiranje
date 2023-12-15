package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.model.exceptions.BookNotFoundException;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookStoreService bookStoreService;
    private final ReviewService reviewService;
    public BookController(BookService bookService, BookStoreService bookStoreService, ReviewService reviewService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
        this.reviewService = reviewService;
    }
    @GetMapping("/{bookId}")
    public String getBookDetails(@PathVariable Long bookId, Model model) {
        model.addAttribute("book", bookService.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId)));
        List<Review> reviews = reviewService.findByBookId(bookId);
        model.addAttribute("reviews",reviews);
        return "bookDetails";
    }


    @GetMapping()
    public String getBookPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.listBooks();
        model.addAttribute("reviews",reviewService);
        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "listBooks";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<BookStore> bookStores = this.bookStoreService.findAll();
            model.addAttribute("bookStores", bookStores);
            model.addAttribute("book", book);
            model.addAttribute("bodyContent", "add-book");
            return "add-book";
        }
        return "redirect:/books?error=BookNotFound";
    }
    @GetMapping("/add-form")
    public String addBookPage(Model model) {
        List<BookStore> bookStores = this.bookStoreService.findAll();
        model.addAttribute("bookStores", bookStores);
        model.addAttribute("bodyContent", "add-product");
        return "add-book";
    }
    @PostMapping("/add")
    public String saveBook(@RequestParam Long id,
                           @RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long bookStoreId){
        this.bookService.save(id,isbn, title, genre, year, bookStoreId);
        return "redirect:/books";
    }
    @GetMapping("/{bookIsbn}/authors")
    public String getBook(@PathVariable String bookIsbn, Model model) {
        Book book=bookService.findBookByIsbn(bookIsbn).orElseThrow(() -> new BookNotFoundException(0L));
        model.addAttribute("book", book);
        if (book!=null){
            List<Review> reviews = reviewService.findByBookId(book.getId());
            model.addAttribute("reviews",reviews);
        }
        return "bookDetails";
    }
}
