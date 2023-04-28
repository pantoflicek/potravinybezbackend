package cz.vse.potravinyBEZ.domain.product;

//Persistence
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;

//Lombok
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindByNameSpecificRequest {
    @NotBlank
    @Length(min = 2, max = 200)
    private String name;
}
