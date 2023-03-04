package cz.vse.potravinyBEZ.domain.user;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank
    @Length(min = 2, max = 45)
    private String username;
    @NotBlank
    @Length(min = 2, max = 60)
    private String password;
    @NotBlank
    @Length(min = 2, max = 60)
    private String email;
}
