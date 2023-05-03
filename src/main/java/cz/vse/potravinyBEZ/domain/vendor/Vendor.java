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
    private String address;
    private String mail;
    private String phone;
    private String contactPerson;
    private String openingHours;
    private String additionalInfo;
}
