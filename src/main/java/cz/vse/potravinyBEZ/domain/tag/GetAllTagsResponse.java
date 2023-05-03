package cz.vse.potravinyBEZ.domain.tag;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTagsResponse {
    private List<Tag> tags;
}
