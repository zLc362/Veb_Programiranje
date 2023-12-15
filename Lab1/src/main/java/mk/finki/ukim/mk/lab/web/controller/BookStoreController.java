package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/bookStore")
public class BookStoreController {
    private final BookStoreService bookStoreService;
    private final BookService bookService;

    public BookStoreController(BookStoreService bookStoreService, BookService bookService) {
        this.bookStoreService = bookStoreService;
        this.bookService = bookService;
    }
    @GetMapping()
    public String getBookStorePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<BookStore> bookStores = this.bookStoreService.findAll();
        model.addAttribute("bookService",bookService);
        model.addAttribute("bookStoreService",bookStoreService);
        model.addAttribute("bookStores", bookStores);
        model.addAttribute("bodyContent", "books");
        return "listBookStores";
    }
    @GetMapping("/edit-form/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        if (this.bookStoreService.findById(id).isPresent()) {
            BookStore bookStore = this.bookStoreService.findById(id).get();
            model.addAttribute("bookStore", bookStore);
            model.addAttribute("bodyContent", "add-book-store");
            return "add-book-store";
        }
        return "redirect:/books?error=BookNotFound";
    }
    @GetMapping("/add-form")
    public String addBookPage(Model model) {
        model.addAttribute("bodyContent", "add-product");
        return "add-book-store";
    }
    @PostMapping("/add")
    public String saveBook(@RequestParam Long id,
                            @RequestParam String name,
                           @RequestParam String adress,
                           @RequestParam String city){
        if (id==1){
            Random rnd = new Random();
            id = rnd.nextLong(1000,10000);
        }
        this.bookStoreService.save(id,name, adress, city);
        return "redirect:/bookStore";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookStoreService.deleteById(id);
        return "redirect:/bookStore";
    }
}
