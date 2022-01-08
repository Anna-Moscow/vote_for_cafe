package springboot.vote_for_cafe.controller;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springboot.vote_for_cafe.error.AppException;

import java.util.Map;

@RestControllerAdvice



    public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
        private final ErrorAttributes errorAttributes;

    public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @ExceptionHandler(AppException.class)
        public ResponseEntity<?> appException(WebRequest request, AppException ex) {

            return createResponseEntity(request, ex.getOptions(), null, ex.getStatus());
        }

// посмотреть видео про этот метод и обработку ошибок из topjava
    // нужны еще какие-то виды исключений? мб notfound?
    // тесты на исключения и security
@SuppressWarnings("unchecked")
    private <T> ResponseEntity<T> createResponseEntity(WebRequest request, ErrorAttributeOptions options, String msg, HttpStatus status) {
        Map<String, Object> body = errorAttributes.getErrorAttributes(request, options);
        if (msg != null) {
            body.put("message", msg);
        }
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        return (ResponseEntity<T>) ResponseEntity.status(status).body(body);
    }
}
