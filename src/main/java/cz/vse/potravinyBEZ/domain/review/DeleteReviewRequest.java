package cz.vse.potravinyBEZ.domain.review;

//Persistence
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

//Lombok
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteReviewRequest {
    @NotBlank
    @Length(min = 2, max = 45)
    private String user;
    @NotBlank
    @Length(min = 2, max = 200)
    private String product;
}
