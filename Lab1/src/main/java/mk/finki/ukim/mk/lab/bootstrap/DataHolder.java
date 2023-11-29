package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books=null;
    public static List<Author> authors=null;
    public static List<BookStore> bookStores=null;
    @PostConstruct
    public void init(){
        books = new ArrayList<>();
        authors = new ArrayList<>();
        bookStores = new ArrayList<>();

        authors.add(new Author(1L,"Stephen","King","Stephen Edwin King is an American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels. Described as the \"King of Horror\", his books have sold more than 350 million copies as of 2006, and many have been adapted into films, television series, miniseries, and comic books."));
        authors.add(new Author(2L,"George","Orwell","Eric Arthur Blair, better known by his pen name George Orwell, was an English novelist, essayist, journalist, and critic. His work is characterised by lucid prose, social criticism, opposition to totalitarianism, and support of democratic socialism."));
        authors.add(new Author(3L,"Lewis","Carrol","Charles Lutwidge Dodgson, better known by his pen name Lewis Carroll, was an English author, poet, mathematician and photographer. His most notable works are Alice's Adventures in Wonderland and its sequel Through the Looking-Glass. He was noted for his facility with word play, logic, and fantasy."));
        authors.add(new Author(4L,"Roald","Dahl","Roald Dahl was a British author of popular children's literature and short stories, a poet, screenwriter and a wartime fighter ace. His books have sold more than 300 million copies worldwide. Dahl has been called \"one of the greatest storytellers for children of the 20th century\"."));
        authors.add(new Author(5L,"James","Patterson","James Brendan Patterson is an American author. Among his works are the Alex Cross, Michael Bennett, Women's Murder Club, Maximum Ride, Daniel X, NYPD Red, Witch & Wizard, Private and Middle School series, as well as many stand-alone thrillers, non-fiction, and romance novels."));

        bookStores.add(new BookStore(1228L,"Bubamara","Negotino","Kire krstev 50"));
        bookStores.add(new BookStore(8844L,"Zivjanka","Stip","Andon Panev 20"));
        bookStores.add(new BookStore(9070L,"Antigona","Skopje","11ti Oktomvri 13"));
        bookStores.add(new BookStore(7291L,"Bubi","Kavadarci","8mi Noemvri 88"));
        bookStores.add(new BookStore(2436L,"Liberta","Struga","Ilindenska 14"));

        books.add(new Book(bookStores.get(0),1239L,"1984","Nineteen Eighty-Four","Sci-Fi",1949));
        books.add(new Book(bookStores.get(1),2541L,"69","Moby-Dick","Adventure",1851));
        books.add(new Book(bookStores.get(2),65123L,"420","Animal Farm","Fiction",1945));
        books.add(new Book(bookStores.get(1),69471L,"256","Frankenstein","Horror",1818));
        books.add(new Book(bookStores.get(3),1285L,"512","The Great Gatsby","Tragedy",1925));
    }


}
