package springboot.vote_for_cafe.service;

import org.springframework.stereotype.Service;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.model.CafeVote;
import springboot.vote_for_cafe.model.Role;
import springboot.vote_for_cafe.model.User;
import springboot.vote_for_cafe.repositiry.CafeRepository;
import springboot.vote_for_cafe.repositiry.UserRepository;
import springboot.vote_for_cafe.repositiry.VoteRepository;
import springboot.vote_for_cafe.util.TimeFilterUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CafeService {

    CafeRepository cafeRepository;
    UserRepository userRepository;
    VoteRepository voteRepository;

    public CafeService(CafeRepository cafeRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.cafeRepository = cafeRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public Cafe save(Cafe cafe) {
        return cafeRepository.save(cafe);

            }


   //пример работы с исключением
      // public void vote(@PathVariable int cafeId) {
        // find user id from  security and findById from Repo
        //Cafe cafe = cafeRepository.findById(cafeId).orElseThrow(() -> new IllegalArgumentException("Id"));
        //
        // voteRepository.save(new CafeVote(user, cafe));}

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
            Optional<CafeVote> vote = voteRepository.findTopByUserAndCreatedBetweenOrderByCreatedDesc(user, start, end);
                if (vote.isPresent()) {
                votes.add(vote.get());
            }
        }

         votes.forEach(vote -> scores.put
               (vote.getCafe().getName(),
                       scores.get(vote.getCafe().getName()) + 1));

      return  scores;
    }
}
