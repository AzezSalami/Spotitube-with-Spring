package nl.han.oose.dea.controller.exceptionmappers;

import nl.han.oose.dea.controller.exceptions.NotAuthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class NotAuthorizedExceptionHandler{

    @ExceptionHandler(value = {NotAuthorizedException.class})
    public ResponseEntity<Object> toResponse(NotAuthorizedException e) {
        return new ResponseEntity<>(e,UNAUTHORIZED);
    }
}
