package cz.vse.potravinyBEZ.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private String response;
}
