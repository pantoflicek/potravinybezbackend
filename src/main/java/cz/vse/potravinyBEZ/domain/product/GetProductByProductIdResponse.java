package cz.vse.potravinyBEZ.domain.product;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductByProductIdResponse {
    private Product product;
}
