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
    @Length(min = 2, max = 200)
    private String website;
}
