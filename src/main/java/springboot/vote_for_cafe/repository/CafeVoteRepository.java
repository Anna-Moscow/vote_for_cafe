package springboot.vote_for_cafe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import springboot.vote_for_cafe.model.CafeVote;
import springboot.vote_for_cafe.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CafeVoteRepository extends JpaRepository<CafeVote, Integer> {

    Optional<CafeVote> findTopByUserAndCreatedBetweenOrderByCreatedDesc(User user, LocalDateTime from, LocalDateTime to);

}
