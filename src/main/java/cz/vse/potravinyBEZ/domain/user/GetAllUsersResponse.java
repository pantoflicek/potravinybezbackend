package cz.vse.potravinyBEZ.domain.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllUsersResponse {
    private List<User> users;
}
