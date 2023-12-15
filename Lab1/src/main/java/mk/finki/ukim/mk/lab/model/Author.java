package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.finki.ukim.mk.lab.convertors.AuthorFullnameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Convert(converter = AuthorFullnameConverter.class)
    public AuthorFullname fullname;
    @Column(length = 4096)
    public String biography;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    public Author() {
    }

    public Author(String name, String surname, String biography) {
        this.fullname=new AuthorFullname(name,surname);
        this.biography = biography;
    }
}
