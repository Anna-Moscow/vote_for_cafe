package springboot.vote_for_cafe.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes")
public class Dish {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String description;

    int price;

    /*@ManyToOne(fetch= FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id", nullable = false)
*/


    //пробуем еще раз
    @ManyToOne
    @JoinColumn(name="cafe_id", nullable=false)
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

    public Integer getId() { return id; }

    public Dish() {
    }

    public Dish(String description, int price) {
        this.description = description;
        this.price = price;
    }
}
