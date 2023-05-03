package cz.vse.potravinyBEZ.domain.role;

//Lombok
import lombok.Builder;
import lombok.Data;

//Java
import java.util.List;

@Builder
@Data
public class GetAllUsersRoleResponse {
    private List<String> roles;
}
