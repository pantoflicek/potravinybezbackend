package cz.vse.potravinyBEZ.repository;

import cz.vse.potravinyBEZ.repository.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<TagEntity, Long> {
    TagEntity findByNameIsLike(String tag);
}
