package cz.vse.potravinyBEZ.controller;

//Persistence
import cz.vse.potravinyBEZ.domain.tag.*;
import cz.vse.potravinyBEZ.service.TagService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tags")
@CrossOrigin(origins = "http://localhost:3000/")
@AllArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping
    public CreateTagResponse createTag(@Valid @NonNull @RequestBody CreateTagRequest createTagRequest){
        return tagService.createTag(createTagRequest);
    }

    @DeleteMapping
    @Transactional
    public DeleteTagResponse deleteTag(@Valid @NonNull @RequestBody DeleteTagRequest deleteTagRequest){
        return tagService.deleteTag(deleteTagRequest);
    }

    @GetMapping
    public GetAllTagsResponse getAllTags(){
        return tagService.getAllTags();
    }

    @PutMapping("/addTag")
    public AddTagToProductResponse addTagToProduct(@Valid @NonNull @RequestBody AddTagToProductRequest addTagToProductRequest){
        return tagService.addTagToProduct(addTagToProductRequest);
    }

    @PostMapping("/productTags")
    public GetAllProductTagsResponse getAllProductTags(@Valid @NonNull @RequestBody GetAllProductTagsRequest getAllProductTagsRequest){
        return tagService.getAllProductTags(getAllProductTagsRequest);
    }

    @DeleteMapping("/productTagDelete")
    public DeleteProductTagResponse deleteProductTag(@Valid @NonNull @RequestBody DeleteProductTagRequest deleteProductTagRequest){
        return tagService.deleteProductTag(deleteProductTagRequest);
    }
}
