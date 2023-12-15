package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Optional<Review> save(Integer score, String description, Book book, LocalDateTime timestamp);
    List<Review> findByBookId(Long id);
    Double findAvgReviewScoreByBookId(Long id);


    List<Review> findAll();

    List<Review> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
