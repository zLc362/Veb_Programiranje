package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Author {
    public Long id;
    public String name;
    public String surname;
    public String biography;

    public Author(Long id, String name, String surname, String biography) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }
}
