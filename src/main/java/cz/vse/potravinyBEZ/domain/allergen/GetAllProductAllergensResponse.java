package cz.vse.potravinyBEZ.domain.allergen;

//Lombok
import lombok.Builder;
import lombok.Data;

//Java
import java.util.List;

@Builder
@Data
public class GetAllProductAllergensResponse {
    private List<String> allergens;
}
