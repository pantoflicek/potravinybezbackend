package cz.vse.potravinyBEZ.domain.vendor;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetVendorByIdResponse {
    private Vendor vendor;
}
