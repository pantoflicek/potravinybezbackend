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
public class GetAllProductReviewsRequest {
    @NotBlank
    @Length(min = 2, max = 200)
    private String product;
}
