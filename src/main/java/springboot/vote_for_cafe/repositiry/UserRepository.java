package springboot.vote_for_cafe.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.vote_for_cafe.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.roles=:role")
    List<User> getAllWithRole(@Param("role") String role);
    // обдумать этот метод!
}
