package mk.finki.ukim.mk.lab.model.exceptions;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(Long id) {
        super(String.format("Review with id %d was not found.",id));
    }
}
