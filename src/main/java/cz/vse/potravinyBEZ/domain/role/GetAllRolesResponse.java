package cz.vse.potravinyBEZ.domain.role;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllRolesResponse {
    private List<Role> roles;
}
