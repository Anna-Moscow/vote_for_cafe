package springboot.vote_for_cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.vote_for_cafe.model.User;
import springboot.vote_for_cafe.service.DishService;
import springboot.vote_for_cafe.service.UserService;

import java.util.List;

public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    public List<User> getAllWithRole(String role) {
        return service.getAllWithRole(role);
    }
}
