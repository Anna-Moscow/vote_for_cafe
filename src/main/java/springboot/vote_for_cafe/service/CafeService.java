package springboot.vote_for_cafe.service;

import org.springframework.stereotype.Service;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.model.CafeVote;
import springboot.vote_for_cafe.model.Role;
import springboot.vote_for_cafe.model.User;
import springboot.vote_for_cafe.repositiry.CafeRepository;
import springboot.vote_for_cafe.repositiry.UserRepository;
import springboot.vote_for_cafe.repositiry.VoteRepository;

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
        return cafeRepository.saveAndFlush(cafe);

            }


   //пример работы с исключением
      // public void vote(@PathVariable int cafeId) {
        // find user id from  security and findById from Repo
        //Cafe cafe = cafeRepository.findById(cafeId).orElseThrow(() -> new IllegalArgumentException("Id"));
        //
        // voteRepository.save(new CafeVote(user, cafe));}


    public Map<String, Integer> getAllWithVotes() {


        Map<String, Integer> scores = new HashMap<>();
        cafeRepository.findAll().forEach(cafe -> scores.put(cafe.getName(), 0));

        List<CafeVote> votes = new ArrayList<>();
        LocalDateTime start = LocalDate.now().atTime(0, 0, 00);
        LocalDateTime end = LocalDate.now().atTime(10, 59, 59);

        List<User> users = userRepository.findUserByRoles(Role.USER); // размер 3 -ok
                //Stream.of(Role.USER)
                //.collect(Collectors.toCollection(HashSet::new)));

        for (User user : users) {
            Optional<CafeVote> vote = voteRepository.findTopByUserAndCreatedBetweenOrderByCreatedDesc(user, start, end);
            //Optional<CafeVote> vote = voteRepository.findTopByUserOrderByCreatedDesc(user); без фильтра по дате тоже 0
            if (vote.isPresent()) {
                votes.add(vote.get());
            }
        }
        //scores.put(users.get(0).getName(), users.size());
        // scores.put("Кафе Эстерхази", 1);
         votes.forEach(vote -> scores.put
               (vote.getCafe().getName(),
                       scores.get(vote.getCafe().getName()) + /*[и так тоже 0]*/  1));

        return scores;

//        return scores.entrySet().stream()
//            .sorted(Comparator.comparingInt(Map.Entry::getValue))
//            .collect(Collectors.toList());
    }
}
