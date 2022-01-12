package springboot.vote_for_cafe.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class CafeVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created", nullable = false)
    @NotNull
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "1")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cafe_id", nullable = false)
    @JsonBackReference(value = "cafeToCafeVote")
    private Cafe cafe;

    public CafeVote(Integer id, LocalDateTime created) {
        this.id = id;
        this.created = created;
    }

    public CafeVote() {
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public User getUser() {
        return user;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    @Override
    public String toString() {
        return "{CafeVote: id - " + id + ", user - " + user + ", cafe: " + cafe + "}";
    }

}
