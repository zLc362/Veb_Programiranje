package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class AuthorRepository {
    public List<Author> authors;

    public AuthorRepository() {
        authors = new ArrayList<>();
        authors.add(new Author(1L,"Stephen","King","Stephen Edwin King is an American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels. Described as the \"King of Horror\", his books have sold more than 350 million copies as of 2006, and many have been adapted into films, television series, miniseries, and comic books."));
        authors.add(new Author(2L,"George","Orwell","Eric Arthur Blair, better known by his pen name George Orwell, was an English novelist, essayist, journalist, and critic. His work is characterised by lucid prose, social criticism, opposition to totalitarianism, and support of democratic socialism."));
        authors.add(new Author(3L,"Lewis","Carrol","Charles Lutwidge Dodgson, better known by his pen name Lewis Carroll, was an English author, poet, mathematician and photographer. His most notable works are Alice's Adventures in Wonderland and its sequel Through the Looking-Glass. He was noted for his facility with word play, logic, and fantasy."));
        authors.add(new Author(4L,"Roald","Dahl","Roald Dahl was a British author of popular children's literature and short stories, a poet, screenwriter and a wartime fighter ace. His books have sold more than 300 million copies worldwide. Dahl has been called \"one of the greatest storytellers for children of the 20th century\"."));
        authors.add(new Author(5L,"James","Patterson","James Brendan Patterson is an American author. Among his works are the Alex Cross, Michael Bennett, Women's Murder Club, Maximum Ride, Daniel X, NYPD Red, Witch & Wizard, Private and Middle School series, as well as many stand-alone thrillers, non-fiction, and romance novels."));
    }
    public List<Author> findAll(){
        return this.authors;
    }
    public Optional<Author> findById(Long id){
        return authors.stream().filter(x->x.id.equals(id)).findFirst();
    }
}
