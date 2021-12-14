package springboot.vote_for_cafe;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.vote_for_cafe.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
