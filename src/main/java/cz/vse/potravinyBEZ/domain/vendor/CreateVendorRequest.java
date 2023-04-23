package cz.vse.potravinyBEZ.domain.vendor;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVendorRequest {
    @NotBlank
    @Length(min = 2, max = 45)
    private String name;
    @Length(min = 2, max = 1024)
    private String logoPath;
    @Length(max = 200)
    private String website;
    @Length(max = 200)
    private String address;
    @Length(max = 45)
    private String mail;
    @Length(max = 200)
    private String phone;
    @Length(max = 200)
    private String contactPerson;
    @Length(max = 1024)
    private String openingHours;
    @Length(max = 1024)
    private String additionalInfo;
}
