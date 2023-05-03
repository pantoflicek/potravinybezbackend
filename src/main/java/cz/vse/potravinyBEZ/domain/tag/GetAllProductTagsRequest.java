package cz.vse.potravinyBEZ.domain.tag;

//Persistence
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

//Lombok
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductTagsRequest {
    @NotBlank
    @Length(min = 2, max = 200)
    private String product;
}
