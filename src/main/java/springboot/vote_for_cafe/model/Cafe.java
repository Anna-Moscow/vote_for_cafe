package springboot.vote_for_cafe.model;


import com.sun.istack.NotNull;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.awt.MenuItem;
import java.util.HashMap;
import java.util.List;

@Entity
@Table (name ="cafes")
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 50)
    public String name;

    @Column(name = "rating", nullable = false)
    @NotNull
    public Integer rating;


    @OneToMany(mappedBy="cafe")
    private List<Dish> menu;

    public Cafe() {
        
    }
// проверить в проперти


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Cafe(@NotBlank @Size(max = 50) String name) {
        this.name = name;
    }


    public Integer getId() { return id; }
}
