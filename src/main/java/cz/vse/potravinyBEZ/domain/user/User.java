package cz.vse.potravinyBEZ.domain.user;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
}
