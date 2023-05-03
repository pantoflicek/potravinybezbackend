package cz.vse.potravinyBEZ.domain.vendor;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllVendorsResponse {
    private List<Vendor> vendors;
}
