package cz.vse.potravinyBEZ.domain.vendor;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Vendor {
    private int id;
    private String name;
    private String logoPath;
    private String website;
}
