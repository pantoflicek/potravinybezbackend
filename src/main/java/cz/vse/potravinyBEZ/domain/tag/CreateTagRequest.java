package cz.vse.potravinyBEZ.domain.tag;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTagRequest {
    @NotBlank
    @Length(min = 2, max = 45)
    private String name;
}
