package cz.vse.potravinyBEZ.service.exception.userService;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailTakenException extends ResponseStatusException {
    public EmailTakenException(){
        super(HttpStatus.CONFLICT, "EMAIL_IS_ALREADY_TAKEN");
    }
}
