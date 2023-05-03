package cz.vse.potravinyBEZ.domain.role;

//Persistence
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

//Lombok
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleRequest {
    @NotBlank
    @Length(min = 2, max = 45)
    private String name;
}
