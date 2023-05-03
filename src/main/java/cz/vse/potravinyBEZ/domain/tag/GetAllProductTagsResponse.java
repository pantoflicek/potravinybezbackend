package cz.vse.potravinyBEZ.domain.tag;

//Lombok
import lombok.Builder;
import lombok.Data;

//Java
import java.util.List;

@Builder
@Data
public class GetAllProductTagsResponse {
    private List<String> tags;
}
