package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.tag.*;
import cz.vse.potravinyBEZ.repository.ProductRepo;
import cz.vse.potravinyBEZ.repository.ProductTagRepo;
import cz.vse.potravinyBEZ.repository.TagRepo;
import cz.vse.potravinyBEZ.repository.entity.ProductEntity;
import cz.vse.potravinyBEZ.repository.entity.ProductTagEntity;
import cz.vse.potravinyBEZ.repository.entity.TagEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock
    private TagRepo tagRepoMock;
    @Mock
    private ProductRepo productRepoMock;
    @Mock
    private ProductTagRepo productTagRepoMock;

    @InjectMocks
    private TagServiceImpl tagService;

    TagEntity tagEntity = TagEntity.builder()
            .id(1)
            .name("tag")
            .build();

    Tag tag = Tag.builder()
            .id(1)
            .name("tag")
            .build();

    ProductEntity productEntity = ProductEntity.builder()
            .id(1)
            .name("product")
            .build();

    ProductTagEntity productTagEntity = ProductTagEntity.builder()
            .id(1)
            .product(productEntity)
            .tag(tagEntity)
            .build();

    @Test
    void createTag() {
        when(tagRepoMock.findByNameIsLike("tag")).thenReturn(null);
        CreateTagRequest request = CreateTagRequest.builder()
                .name("tag")
                .build();
        CreateTagResponse eR = CreateTagResponse.builder()
                .response("Tag: tag has been created!")
                .build();
        CreateTagResponse aR = tagService.createTag(request);
        assertEquals(eR, aR);
    }

    @Test
    void getAllTags() {
        when(tagRepoMock.findAll()).thenReturn(List.of(tagEntity));
        GetAllTagsResponse eR = GetAllTagsResponse.builder()
                .tags(List.of(tag))
                .build();
        GetAllTagsResponse aR = tagService.getAllTags();
        assertEquals(eR, aR);
    }

    @Test
    void deleteTag() {
        when(tagRepoMock.findByNameIsLike("tag")).thenReturn(tagEntity);
        DeleteTagRequest request = DeleteTagRequest.builder()
                .name("tag")
                .build();
        DeleteTagResponse eR = DeleteTagResponse.builder()
                .response("Tag: tag has been deleted!")
                .build();
        DeleteTagResponse aR = tagService.deleteTag(request);
        assertEquals(eR, aR);
    }

    @Test
    void addTagToProduct() {
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(tagRepoMock.findByNameIsLike("tag")).thenReturn(tagEntity);
        when(productTagRepoMock.findByProductAndTagId(1,1)).thenReturn(null);
        AddTagToProductRequest request = AddTagToProductRequest.builder()
                .product("product")
                .tag("tag")
                .build();
        AddTagToProductResponse er = AddTagToProductResponse.builder()
                .response("Tag: tag has been added to product: product!")
                .build();
        AddTagToProductResponse ar = tagService.addTagToProduct(request);
        assertEquals(er, ar);
    }

    @Test
    void deleteProductTag() {
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(tagRepoMock.findByNameIsLike("tag")).thenReturn(tagEntity);
        when(productTagRepoMock.findByProductAndTagId(1,1)).thenReturn(productTagEntity);
        DeleteProductTagRequest request = DeleteProductTagRequest.builder()
                .product("product")
                .tag("tag")
                .build();
        DeleteProductTagResponse er = DeleteProductTagResponse.builder()
                .response("Tag: tag was deleted from product: product!")
                .build();
        DeleteProductTagResponse ar = tagService.deleteProductTag(request);
        assertEquals(er,ar);
    }

    @Test
    void getAllProductTags() {
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(productTagRepoMock.findAllTagsByProductIdIsLike(1)).thenReturn(List.of("tag"));
        GetAllProductTagsRequest request = GetAllProductTagsRequest.builder()
                .product("product")
                .build();
        GetAllProductTagsResponse er = GetAllProductTagsResponse.builder()
                .tags(List.of("tag"))
                .build();
        GetAllProductTagsResponse ar = tagService.getAllProductTags(request);
        assertEquals(er,ar);
    }
}