package springboot.vote_for_cafe.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name ="cafes")
public class Cafe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 50)
    public String name;


    @OneToMany(mappedBy="cafe")
    @JsonManagedReference
    private List<Dish> dishes;

    public Cafe() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cafe( String name) {
        this.name = name;
    }

    public Cafe(Integer id,  String name) {
        this.id = id;
        this.name = name;
            }



    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "{Cafe: id - " + id + ", name - " + name + "}";
    }
}
