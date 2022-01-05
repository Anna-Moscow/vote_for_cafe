package springboot.vote_for_cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springboot.vote_for_cafe.model.User;

import java.util.List;

@RestController
@RequestMapping(value = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {


   // @Autowired
   // private UserService service;

   // @Autowired
    //private UniqueMailValidator emailValidator;

   // @InitBinder
    //protected void initBinder(WebDataBinder binder) {
       // binder.addValidators(emailValidator);
   // }

    //это точно нужно
    @GetMapping
    public User get(@AuthenticationPrincipal AuthUser authUser) {
        return authUser.getUser();
    }

    // мб метод register(см видео 5.1 из spring boot)
}
