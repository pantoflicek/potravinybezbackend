package cz.vse.potravinyBEZ.domain.product;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsResponse {
    private List<Product> products;
}
