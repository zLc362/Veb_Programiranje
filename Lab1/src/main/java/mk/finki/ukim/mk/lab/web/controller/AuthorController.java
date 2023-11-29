package mk.finki.ukim.mk.lab.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String addAuthorList(@RequestParam String bookIsbn, Model model){
        model.addAttribute("authors", authorService.listAuthors());
        model.addAttribute("isbn",bookIsbn);
        return "authorList";
    }

    @PostMapping
    public String addAuthorToBookPost(@RequestParam String bookIsbn, @RequestParam Long authorId){
        bookService.addAuthorToBook(authorId, bookIsbn);
        return "redirect:/books/"+bookIsbn+"/authors";
    }

    @GetMapping("/books")
    public String addAuthorToBookeGet(@RequestParam Long authorId, Model model){
        model.addAttribute("authorId", authorId);
        return "authorBooks";
    }


}
