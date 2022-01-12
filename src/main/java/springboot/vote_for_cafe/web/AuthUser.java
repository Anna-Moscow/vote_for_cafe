package springboot.vote_for_cafe.web;

import org.springframework.lang.NonNull;
import springboot.vote_for_cafe.model.User;


public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public User getUser() {
        return user;
    }

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.getId();
    }

}

