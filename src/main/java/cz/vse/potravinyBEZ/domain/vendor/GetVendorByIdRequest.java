package cz.vse.potravinyBEZ.domain.vendor;
//Lombok
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetVendorByIdRequest {
    private int id;
}
