package springboot.vote_for_cafe.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

public class IllegalRequestDataException extends AppException {

    public IllegalRequestDataException(String msg) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, msg,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));
    }

}
