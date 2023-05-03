package cz.vse.potravinyBEZ.service.exception.userService;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PasswordTooShortException extends ResponseStatusException {
    public PasswordTooShortException(){
        super(HttpStatus.BAD_REQUEST, "PASSWORD_TOO_SHORT");
    }
}
