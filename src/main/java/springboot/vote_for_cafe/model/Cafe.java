package springboot.vote_for_cafe.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "cafes")
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    @Size(max = 50)
    public String name;

    @OneToMany(mappedBy = "cafe")
    @JsonManagedReference(value = "cafeToDish")
    private List<Dish> dishes;

    @OneToMany(mappedBy = "cafe")
    @JsonManagedReference(value = "cafeToCafeVote")
    private List<CafeVote> votes;

    public Cafe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cafe(String name) {
        this.name = name;
    }

    public Cafe(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<CafeVote> getVotes() {
        return votes;
    }

    public void setVotes(List<CafeVote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "{Cafe: id - " + id + ", name - " + name + "}";
    }

}
