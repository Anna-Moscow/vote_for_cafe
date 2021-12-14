package springboot.vote_for_cafe.model;

import springboot.vote_for_cafe.model.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table (name ="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 20)
    public String name;

    @Column(name = "surname", nullable = false)
    @NotBlank
    @Size(max = 30)
    public String surname;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_roles")})
    @Column(name = "role")
    @JoinColumn(name = "user_id")
    public Set<Role> roles;
       // по умолчанию юзер и убрать из конструктора

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public User() {
    }

    public User(@NotBlank @Size(max = 20) String name, @NotBlank @Size(max = 30) String surname) {
        this.name = name;
        this.surname = surname;
            }


}
