package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.jpa.ReviewRepository;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewService{
    public final ReviewRepository reviewRepository;

    public ReviewServiceImplementation(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> save(Integer score, String description, Book book, LocalDateTime timestamp) {
        return Optional.of(reviewRepository.save(new Review(score,description,book,timestamp)));
    }

    @Override
    public List<Review> findByBookId(Long id) {
        return reviewRepository.findByBookId(id);
    }

    @Override
    public Double findAvgReviewScoreByBookId(Long id) {
        List<Review> revs = reviewRepository.findByBookId(id);
        if (revs.size()==0)
            return 0.0;
        return revs.stream().mapToInt(Review::getScore).sum()/(double)revs.size();
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to) {
        return reviewRepository.findAllByTimestampBetween(from,to);
    }
}
