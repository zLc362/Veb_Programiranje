package mk.finki.ukim.mk.lab.model.exceptions;


public class BookStoreNotFoundException extends RuntimeException{
    public BookStoreNotFoundException(Long id) {
        super(String.format("BookStore with id %d was not found.",id));
    }
}
