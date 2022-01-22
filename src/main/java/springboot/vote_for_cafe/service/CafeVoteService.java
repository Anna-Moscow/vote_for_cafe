package springboot.vote_for_cafe.service;

import org.springframework.stereotype.Service;
import springboot.vote_for_cafe.model.CafeVote;
import springboot.vote_for_cafe.repository.CafeRepository;
import springboot.vote_for_cafe.repository.CafeVoteRepository;
import springboot.vote_for_cafe.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class CafeVoteService {

    public CafeVoteRepository cafeVoteRepository;

    public CafeRepository cafeRepository;

    public UserRepository userRepository;

    public CafeVoteService(CafeVoteRepository cafeVoteRepository, CafeRepository cafeRepository, UserRepository userRepository) {
        this.cafeVoteRepository = cafeVoteRepository;
        this.cafeRepository = cafeRepository;
        this.userRepository = userRepository;
    }

    public CafeVote save(CafeVote cafeVote, int userId, int cafeId) {
        cafeVote.setCreated(LocalDateTime.now());
        cafeVote.setCafe(cafeRepository.getById(cafeId));
        cafeVote.setUser(userRepository.getById(userId));
        return cafeVoteRepository.save(cafeVote);
    }

}
