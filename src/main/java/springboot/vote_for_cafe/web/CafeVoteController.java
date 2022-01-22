package springboot.vote_for_cafe.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.vote_for_cafe.model.CafeVote;
import springboot.vote_for_cafe.service.CafeVoteService;
import springboot.vote_for_cafe.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CafeVoteController {

    private final CafeVoteService cafeVoteService;

    public CafeVoteController(CafeVoteService cafeVoteService) {
        this.cafeVoteService = cafeVoteService;
    }

    @PostMapping("api/cafes/{cafeId}/votes")
    public ResponseEntity<CafeVote> create(@AuthenticationPrincipal AuthUser authUser,
                                           @PathVariable int cafeId, @Valid @RequestBody CafeVote cafeVote) {
        int userId = authUser.id();
        ValidationUtil.checkNew(cafeVote);
        CafeVote created = cafeVoteService.save(cafeVote, userId, cafeId);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/cafes" + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

}
