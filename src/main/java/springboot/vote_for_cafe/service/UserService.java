package springboot.vote_for_cafe.service;


import springboot.vote_for_cafe.model.User;
import springboot.vote_for_cafe.repositiry.UserRepository;

import java.util.List;

public class UserService {

    UserRepository repository;

    public UserService(UserRepository repository)
    { this.repository = repository; }

    public List<User> getAllWithRole(String role) {
       return repository.getAllWithRole(role);
   }
}
