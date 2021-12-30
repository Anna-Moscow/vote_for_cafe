package springboot.vote_for_cafe.model;


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

    @ManyToOne // не 1 к 1? голосов много, а засчитываетс только последний
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToOne
    @JoinColumn(name="cafe_id", nullable=false)
    private Cafe cafe;

    public CafeVote(User user, Cafe cafe, LocalDateTime created) {
        this.created = created;
        this.user = user;
        this.cafe = cafe;
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
}