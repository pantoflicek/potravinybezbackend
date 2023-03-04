package cz.vse.potravinyBEZ.service.impl;

//Spring
import cz.vse.potravinyBEZ.domain.converter.EntityToProductConverter;
import cz.vse.potravinyBEZ.domain.converter.EntityToUserConverter;
import cz.vse.potravinyBEZ.domain.review.*;
import cz.vse.potravinyBEZ.repository.ProductRepo;
import cz.vse.potravinyBEZ.repository.ReviewRepo;
import cz.vse.potravinyBEZ.repository.UserRepo;
import cz.vse.potravinyBEZ.repository.entity.ProductEntity;
import cz.vse.potravinyBEZ.repository.entity.ReviewEntity;
import cz.vse.potravinyBEZ.repository.entity.UserEntity;
import cz.vse.potravinyBEZ.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//Java
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final ReviewRepo reviewRepo;

    @Override
    public CreateReviewResponse createReview(CreateReviewRequest review) {
        ProductEntity addingProduct = productRepo.findByNameIsLike(review.getProduct());
        UserEntity addingUser = userRepo.findByUsernameIsLike(review.getUser());
        ReviewEntity newReview = ReviewEntity.builder()
                .date(review.getDate())
                .text(review.getText())
                .stars(review.getStars())
                .user(addingUser)
                .product(addingProduct)
                .build();
        if (addingProduct == null){
            return CreateReviewResponse.builder()
                    .response("Specified product does not exists!")
                    .build();
        } else if (addingUser == null) {
            return CreateReviewResponse.builder()
                    .response("Specified user does not exists!")
                    .build();
        } else {
            ReviewEntity isExisting = reviewRepo.findByProductIdAndUserId(addingProduct.getId(), addingUser.getId());
            if (Objects.isNull(isExisting)){
                reviewRepo.save(newReview);
                return CreateReviewResponse.builder()
                        .response("Product " + addingProduct.getName() + " has new review from user: " + addingUser.getUsername())
                        .build();
            } else {
                return CreateReviewResponse.builder()
                        .response("Specified product already has a review from this user!")
                        .build();
            }
        }
    }

    @Override
    public DeleteReviewResponse deleteReview(DeleteReviewRequest review) {
        ProductEntity deletingProduct = productRepo.findByNameIsLike(review.getProduct());
        UserEntity deletingUser = userRepo.findByUsernameIsLike(review.getUser());
        if (deletingUser == null){
            return DeleteReviewResponse.builder()
                    .response("Specified user does not exists!")
                    .build();
        } else if (deletingProduct == null) {
            return DeleteReviewResponse.builder()
                    .response("Specified product does not exists!")
                    .build();
        } else {
            ReviewEntity reviewToDelete = reviewRepo.findByProductIdAndUserId(deletingProduct.getId(), deletingUser.getId());
            if (Objects.isNull(reviewToDelete)){
                return DeleteReviewResponse.builder()
                        .response("Product does not have the specified review!")
                        .build();
            } else {
                reviewRepo.delete(reviewToDelete);
                return DeleteReviewResponse.builder()
                        .response("Review from user: " + deletingUser.getUsername() + " has been deleted from product: " + deletingProduct.getName())
                        .build();
            }
        }
    }

    @Override
    public GetAllProductReviewsResponse getAllProductReviews(GetAllProductReviewsRequest request) {
        ProductEntity product = productRepo.findByNameIsLike(request.getProduct());
        if (product == null){
            System.out.println("No product found");
            return null;
        } else {
            List<ReviewEntity> reviewEntities = reviewRepo.findByProductId(product.getId());
            List<Review> reviews = reviewEntities.stream()
                    .map(reviewEntity -> new Review(reviewEntity.getId(), reviewEntity.getDate(), reviewEntity.getText(), reviewEntity.getStars(), EntityToProductConverter.convert(reviewEntity.getProduct()), EntityToUserConverter.convert(reviewEntity.getUser())))
                    .toList();
            return GetAllProductReviewsResponse.builder()
                    .reviews(reviews)
                    .build();
        }
    }
}
