package cz.vse.potravinyBEZ.domain.review;

//Persistence
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

//Lombok
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest {
    @NotBlank
    @Length(min = 2, max = 200)
    private String product;
    @NotBlank
    @Length(min = 2, max = 45)
    private String user;
    private Date date;
    private String text;
    private int stars;
}
