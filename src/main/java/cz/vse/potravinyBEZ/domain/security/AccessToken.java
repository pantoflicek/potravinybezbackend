package cz.vse.potravinyBEZ.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Lombok
import lombok.*;

//Java
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken {
    private String subject;
    private List<String> roles;
    private int userId;

    @JsonIgnore
    public boolean hasRole(String roleName) {
        if (roles == null) {
            return false;
        }
        return roles.contains(roleName);
    }
}
