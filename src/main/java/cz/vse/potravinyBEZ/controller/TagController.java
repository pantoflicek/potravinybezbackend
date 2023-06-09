package cz.vse.potravinyBEZ.controller;

//Persistence
import cz.vse.potravinyBEZ.configuration.security.isAuth.IsAuthenticated;
import cz.vse.potravinyBEZ.domain.tag.*;
import cz.vse.potravinyBEZ.service.TagService;
import jakarta.annotation.security.RolesAllowed;
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

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping
    public CreateTagResponse createTag(@Valid @NonNull @RequestBody CreateTagRequest createTagRequest){
        return tagService.createTag(createTagRequest);
    }

    @DeleteMapping
    @Transactional
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public DeleteTagResponse deleteTag(@Valid @NonNull @RequestBody DeleteTagRequest deleteTagRequest){
        return tagService.deleteTag(deleteTagRequest);
    }

    @GetMapping
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public GetAllTagsResponse getAllTags(){
        return tagService.getAllTags();
    }

    @PutMapping("/addTag")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public AddTagToProductResponse addTagToProduct(@Valid @NonNull @RequestBody AddTagToProductRequest addTagToProductRequest){
        return tagService.addTagToProduct(addTagToProductRequest);
    }

    @PostMapping("/productTags")
    public GetAllProductTagsResponse getAllProductTags(@Valid @NonNull @RequestBody GetAllProductTagsRequest getAllProductTagsRequest){
        return tagService.getAllProductTags(getAllProductTagsRequest);
    }

    @DeleteMapping("/productTagDelete")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public DeleteProductTagResponse deleteProductTag(@Valid @NonNull @RequestBody DeleteProductTagRequest deleteProductTagRequest){
        return tagService.deleteProductTag(deleteProductTagRequest);
    }
}
