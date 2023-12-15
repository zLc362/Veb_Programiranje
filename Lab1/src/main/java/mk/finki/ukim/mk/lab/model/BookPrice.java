package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
@Entity
public class BookPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double price;
    private Double discount;

    public BookPrice() {
    }
    public BookPrice(Double price) {
        this.price = price;
    }
}
