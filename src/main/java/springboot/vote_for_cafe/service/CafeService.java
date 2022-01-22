package springboot.vote_for_cafe.service;

import org.springframework.stereotype.Service;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.model.CafeVote;
import springboot.vote_for_cafe.model.Role;
import springboot.vote_for_cafe.model.User;
import springboot.vote_for_cafe.repository.CafeRepository;
import springboot.vote_for_cafe.repository.UserRepository;
import springboot.vote_for_cafe.repository.CafeVoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class CafeService {

    CafeRepository cafeRepository;
    UserRepository userRepository;
    CafeVoteRepository cafeVoteRepository;

    public CafeService(CafeRepository cafeRepository, UserRepository userRepository, CafeVoteRepository cafeVoteRepository) {
        this.cafeRepository = cafeRepository;
        this.userRepository = userRepository;
        this.cafeVoteRepository = cafeVoteRepository;
    }

    public Cafe save(Cafe cafe) {
        return cafeRepository.save(cafe);
    }

    public List<Cafe> getAll() {
        return cafeRepository.findAll();
    }

    public Map<String, Integer> getAllWithVotes() {
        Map<String, Integer> scores = new HashMap<>();
        cafeRepository.findAll().forEach(cafe -> scores.put(cafe.getName(), 0));
        List<CafeVote> votes = new ArrayList<>();

        LocalDateTime start = LocalDate.now().atTime(0, 0, 0);
        LocalDateTime end = LocalDate.now().atTime(10, 59, 59);

        List<User> users = userRepository.findUserByRoles(Role.USER);

        for (User user : users) {
            Optional<CafeVote> vote = cafeVoteRepository
                    .findTopByUserAndCreatedBetweenOrderByCreatedDesc(user, start, end);
            vote.ifPresent(votes::add);
        }

        votes.forEach(vote -> scores.put
                (vote.getCafe().getName(),
                        scores.get(vote.getCafe().getName()) + 1));

        return scores;
    }

}
