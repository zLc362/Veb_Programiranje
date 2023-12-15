package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorFullname implements Serializable {
    public String name;
    public String surname;

    public AuthorFullname(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public AuthorFullname() {
    }
}
