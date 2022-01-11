package springboot.vote_for_cafe.web;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springboot.vote_for_cafe.model.User;
// нужен ли этот класс, он нигде не используется. В проекте-образце кстати тоже
@RestController
@RequestMapping(value = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {


      @GetMapping
    public User get(@AuthenticationPrincipal AuthUser authUser) {
        return authUser.getUser();
    }

    // мб метод register(см видео 5.1 из spring boot)
}
