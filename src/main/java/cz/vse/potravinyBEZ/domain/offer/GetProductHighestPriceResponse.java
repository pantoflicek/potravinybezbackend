package cz.vse.potravinyBEZ.domain.offer;

//Lombok
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetProductHighestPriceResponse {
    private int offer;
}
