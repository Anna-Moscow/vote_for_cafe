package springboot.vote_for_cafe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"cafe_id", "description"}, name = "dish_unique_cafe_description_idx")})

public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(max = 80)
    String description;

    @Column(name = "price", nullable = false)
    @NotNull
    int price;

    @ManyToOne
    @JoinColumn(name = "cafe_id", nullable = false)
    @JsonBackReference(value = "cafeToDish")
    Cafe cafe;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Dish() {
    }

    public Dish(String description, int price) {
        this.description = description;
        this.price = price;
    }

    public Dish(Integer id, String description, int price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    @Override
    public String toString() {
        return "{Dish: id - " + id + ", description - " + description + ", price: " + price + "}";
    }

}
