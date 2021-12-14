package springboot.vote_for_cafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springboot.vote_for_cafe.model.Role;
import springboot.vote_for_cafe.model.User;

@SpringBootApplication
public class VoteForCafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteForCafeApplication.class, args);
    }


}
