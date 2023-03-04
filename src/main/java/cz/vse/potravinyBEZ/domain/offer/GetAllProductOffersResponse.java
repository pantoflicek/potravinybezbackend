package cz.vse.potravinyBEZ.domain.offer;

//Lombok
import lombok.Builder;
import lombok.Data;

//Java
import java.util.List;

@Builder
@Data
public class GetAllProductOffersResponse {
    private List<Offer> offers;
}
