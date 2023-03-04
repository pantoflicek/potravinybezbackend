package cz.vse.potravinyBEZ.service.exception.userService;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException(){
        super(HttpStatus.CONFLICT, "USER_ALREADY_EXISTS");
    }
}
