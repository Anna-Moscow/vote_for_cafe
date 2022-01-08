package springboot.vote_for_cafe.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.vote_for_cafe.model.Role;
import springboot.vote_for_cafe.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {

   // @Query("SELECT u FROM User u JOIN u.roles WHERE role = :role")

    List<User> findUserByRoles(Role role);
    //нужно добавить анотацию manytomany к полю roles? передать SET?

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);
}
