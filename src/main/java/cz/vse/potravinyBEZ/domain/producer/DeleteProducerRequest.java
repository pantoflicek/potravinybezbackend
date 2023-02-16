package cz.vse.potravinyBEZ.domain.producer;

//Persistence
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;

//Lombok
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProducerRequest {
    @NotBlank
    @Length(min = 2, max = 45)
    private String name;
}
