package cz.vse.potravinyBEZ.domain.product;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindLastFiveProductsResponse {
    private List<Product> products;
}
