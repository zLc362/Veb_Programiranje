package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookStoreRepository {

    public List<BookStore> findAll(){
        return DataHolder.bookStores;
    }
    public Optional<BookStore> findById(Long id){
        return DataHolder.bookStores.stream().filter(x->x.getId().equals(id)).findFirst();
    }
    public Optional<BookStore> save(Long id,String name, String adress, String city){
        DataHolder.bookStores.removeIf(x->x.getId().equals(id));
        BookStore bookStore = new BookStore(name,city,adress);
        DataHolder.bookStores.add(bookStore);
        return Optional.of(bookStore);
    }
    public void deleteById(Long id){
        DataHolder.bookStores.removeIf(x->x.getId().equals(id));
    }
}
