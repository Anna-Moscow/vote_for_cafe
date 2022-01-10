package springboot.vote_for_cafe.repositiry;


import org.springframework.data.jpa.repository.JpaRepository;
import springboot.vote_for_cafe.model.CafeVote;
import springboot.vote_for_cafe.model.Dish;
import springboot.vote_for_cafe.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<CafeVote, Integer> {

    Optional<CafeVote> findTopByUserAndCreatedBetweenOrderByCreatedDesc(User user, LocalDateTime from , LocalDateTime to);
    //Optional<CafeVote> findCreatedBetweenAndTopByUserOrderByCreatedDesc(User user, LocalDateTime from , LocalDateTime to);
    Optional<CafeVote> findTopByUserOrderByCreatedDesc (User user); // тоже было null
}
