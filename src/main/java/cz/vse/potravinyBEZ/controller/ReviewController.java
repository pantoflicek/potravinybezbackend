package cz.vse.potravinyBEZ.controller;

//Persistence
import cz.vse.potravinyBEZ.domain.review.*;
import cz.vse.potravinyBEZ.service.ReviewService;
import jakarta.validation.Valid;

//Lombok
import lombok.AllArgsConstructor;

//Spring
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reviews")
@CrossOrigin(origins = "http://localhost:3000/")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public CreateReviewResponse createReview(@Valid @NonNull @RequestBody CreateReviewRequest createReviewRequest){
        return reviewService.createReview(createReviewRequest);
    }

    @DeleteMapping
    public DeleteReviewResponse deleteReview(@Valid @NonNull @RequestBody DeleteReviewRequest deleteReviewRequest){
        return reviewService.deleteReview(deleteReviewRequest);
    }

    @PutMapping
    public GetAllProductReviewsResponse getAllProductReviews(@Valid @NonNull @RequestBody GetAllProductReviewsRequest getAllProductReviewsRequest){
        return reviewService.getAllProductReviews(getAllProductReviewsRequest);
    }
}
