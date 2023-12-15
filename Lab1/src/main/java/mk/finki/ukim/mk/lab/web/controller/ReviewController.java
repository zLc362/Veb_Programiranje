package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.model.exceptions.BookNotFoundException;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final BookService bookService;

    public ReviewController(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String showReviewsPage(Model model) {
        List<Review> reviews = this.reviewService.findAll();
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    @GetMapping("/add/{id}")
    public String addReviewPage(@PathVariable Long id, Model model){
        model.addAttribute("book",bookService.findById(id).orElse(null));
        return "add-review";
    }

    @PostMapping("/add")
    public String saveReview(@RequestParam Integer score,
                             @RequestParam String description,
                             @RequestParam Long bookId,
                             @RequestParam LocalDateTime dateTime){
        if (bookId==0){
            return null;
        }
        Book book=bookService.findById(bookId).orElse(null);
        reviewService.save(score,description,book,dateTime);
        return "redirect:/reviews/"+bookId;
    }

    @PostMapping("/filter")
    public String filterReviews(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                LocalDateTime from,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                LocalDateTime to,
                                @RequestParam Long bookId,
                                Model model) {
        List<Review> filteredReviews = reviewService.findAllByTimestampBetween(from, to).stream().filter(review -> review.getBook().getId().equals(bookId)).toList();
        Book book = bookService.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        model.addAttribute("reviews", filteredReviews);
        model.addAttribute("bookService",bookService);
        model.addAttribute("book", book);
        return "bookDetails";
    }
    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        Book book=bookService.findById(id).orElseThrow(() -> new BookNotFoundException(0L));
        model.addAttribute("book", book);
        if (book!=null){
            List<Review> reviews = reviewService.findByBookId(book.getId());
            model.addAttribute("reviews",reviews);
            model.addAttribute("bookService",bookService);
        }
        return "bookDetails";
    }
}
