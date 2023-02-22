package cz.vse.potravinyBEZ.domain.review;

//Lombok
import lombok.Builder;
import lombok.Data;

//Java
import java.util.List;

@Builder
@Data
public class GetAllProductReviewsResponse {
    private List<Review> reviews;
}
