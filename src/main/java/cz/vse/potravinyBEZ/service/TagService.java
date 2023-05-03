package cz.vse.potravinyBEZ.service;

import cz.vse.potravinyBEZ.domain.tag.*;

public interface TagService {
    CreateTagResponse createTag(CreateTagRequest tag);
    GetAllTagsResponse getAllTags();
    DeleteTagResponse deleteTag(DeleteTagRequest tag);
    AddTagToProductResponse addTagToProduct(AddTagToProductRequest request);
    DeleteProductTagResponse deleteProductTag(DeleteProductTagRequest request);
    GetAllProductTagsResponse getAllProductTags(GetAllProductTagsRequest request);
}
