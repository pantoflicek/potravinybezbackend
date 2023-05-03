package cz.vse.potravinyBEZ.service.impl;
import cz.vse.potravinyBEZ.domain.converter.EntityToTagConverter;
import cz.vse.potravinyBEZ.domain.tag.*;
import cz.vse.potravinyBEZ.repository.ProductRepo;
import cz.vse.potravinyBEZ.repository.ProductTagRepo;
import cz.vse.potravinyBEZ.repository.TagRepo;
import cz.vse.potravinyBEZ.repository.entity.ProductEntity;
import cz.vse.potravinyBEZ.repository.entity.ProductTagEntity;
import cz.vse.potravinyBEZ.repository.entity.TagEntity;
import cz.vse.potravinyBEZ.service.TagService;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepo tagRepo;
    private final ProductRepo productRepo;
    private final ProductTagRepo productTagRepo;

    @Override
    public CreateTagResponse createTag(CreateTagRequest tag) {
        TagEntity newTag = TagEntity.builder()
                .name(tag.getName())
                .build();
        TagEntity isExiting = tagRepo.findByNameIsLike(newTag.getName());
        if (Objects.isNull(isExiting)){
            tagRepo.save(newTag);
            return CreateTagResponse.builder()
                    .response("Tag: " + newTag.getName() + " has been created!")
                    .build();
        } else {
            return CreateTagResponse.builder()
                    .response("Tag already exists!")
                    .build();
        }
    }

    @Override
    public GetAllTagsResponse getAllTags() {
        List<TagEntity> allTagEntities = tagRepo.findAll();
        List<Tag> allTags = allTagEntities.stream()
                .map(EntityToTagConverter::convert)
                .toList();
        return GetAllTagsResponse.builder()
                .tags(allTags)
                .build();
    }

    @Override
    public DeleteTagResponse deleteTag(DeleteTagRequest tag) {
        TagEntity tagToDelete = tagRepo.findByNameIsLike(tag.getName());
        if (Objects.isNull(tagToDelete)){
            return DeleteTagResponse.builder()
                    .response("Specified tag does not exists!")
                    .build();
        } else {
            tagRepo.delete(tagToDelete);
            return DeleteTagResponse.builder()
                    .response("Tag: " + tagToDelete.getName() + " has been deleted!")
                    .build();
        }
    }

    @Override
    public AddTagToProductResponse addTagToProduct(AddTagToProductRequest request) {
        ProductEntity addingProduct = productRepo.findByNameIsLike(request.getProduct());
        TagEntity addingTag = tagRepo.findByNameIsLike(request.getTag());
        ProductTagEntity productTagEntity = ProductTagEntity.builder()
                .product(addingProduct)
                .tag(addingTag)
                .build();
        if (addingProduct == null){
            return AddTagToProductResponse.builder()
                    .response("Specified product does not exists!")
                    .build();
        } else if (addingTag == null){
            return AddTagToProductResponse.builder()
                    .response("Specified tag does not exists!")
                    .build();
        } else {
            ProductTagEntity isExisting = productTagRepo.findByProductAndTagId(addingProduct.getId(), addingTag.getId());
            if (Objects.isNull(isExisting)){
                productTagRepo.save(productTagEntity);
                return AddTagToProductResponse.builder()
                        .response("Tag: " + addingTag.getName() + " has been added to product: " + addingProduct.getName() + "!")
                        .build();
            } else {
                return AddTagToProductResponse.builder()
                        .response("Specified product already has this tag!")
                        .build();
            }
        }
    }

    @Override
    public DeleteProductTagResponse deleteProductTag(DeleteProductTagRequest request) {
        ProductEntity deletingProduct = productRepo.findByNameIsLike(request.getProduct());
        TagEntity deletingTag = tagRepo.findByNameIsLike(request.getTag());
        if (deletingProduct == null){
            return DeleteProductTagResponse.builder()
                    .response("Specified product does not exists!")
                    .build();
        } else if (deletingTag == null){
            return DeleteProductTagResponse.builder()
                    .response("Specified tag does not exists!")
                    .build();
        } else {
            ProductTagEntity entityToDelete = productTagRepo.findByProductAndTagId(deletingProduct.getId(), deletingTag.getId());
            if (Objects.isNull(entityToDelete)){
                return DeleteProductTagResponse.builder()
                        .response("Product does not have the specified tag!")
                        .build();
            } else {
                productTagRepo.delete(entityToDelete);
                return DeleteProductTagResponse.builder()
                        .response("Tag: " + deletingTag.getName() + " was deleted from product: " + deletingProduct.getName() + "!")
                        .build();
            }
        }
    }

    @Override
    public GetAllProductTagsResponse getAllProductTags(GetAllProductTagsRequest request) {
        ProductEntity product = productRepo.findByNameIsLike(request.getProduct());
        List<String> list = productTagRepo.findAllTagsByProductIdIsLike(product.getId());
        return GetAllProductTagsResponse.builder()
                .tags(list)
                .build();
    }
}
