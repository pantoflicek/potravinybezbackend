package cz.vse.potravinyBEZ.domain.producer;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProducerRequest {
    @NotBlank
    @Length(min = 2, max = 45)
    private String name;
}
