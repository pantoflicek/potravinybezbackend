package cz.vse.potravinyBEZ.domain.offer;

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
public class CreateOfferRequest {
    @NotBlank
    @Length(min = 2, max = 45)
    private String vendor;
    @NotBlank
    @Length(min = 2, max = 200)
    private String product;
    private Date dateAdded;
    private Date dateTo;
    private Date dateFrom;
    private int price;
}
