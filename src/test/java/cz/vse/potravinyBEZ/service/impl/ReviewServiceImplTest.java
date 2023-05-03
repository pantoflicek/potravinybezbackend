package cz.vse.potravinyBEZ.service.impl;

import cz.vse.potravinyBEZ.domain.producer.Producer;
import cz.vse.potravinyBEZ.domain.product.Product;
import cz.vse.potravinyBEZ.domain.review.*;
import cz.vse.potravinyBEZ.domain.user.User;
import cz.vse.potravinyBEZ.repository.ProductRepo;
import cz.vse.potravinyBEZ.repository.ReviewRepo;
import cz.vse.potravinyBEZ.repository.UserRepo;
import cz.vse.potravinyBEZ.repository.entity.ProducerEntity;
import cz.vse.potravinyBEZ.repository.entity.ProductEntity;
import cz.vse.potravinyBEZ.repository.entity.ReviewEntity;
import cz.vse.potravinyBEZ.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ProductRepo productRepoMock;
    @Mock
    private UserRepo userRepoMock;
    @Mock
    private ReviewRepo reviewRepoMock;

    @InjectMocks
    private ReviewServiceImpl reviewService;


    UserEntity userEntity = UserEntity.builder()
            .id(1)
            .username("user")
            .build();

    User user = User.builder()
            .id(1)
            .username("user")
            .build();

    ProducerEntity producerEntity = ProducerEntity.builder()
            .name("producer")
            .id(1)
            .build();

    Producer producer = Producer.builder()
            .name("producer")
            .id(1)
            .build();

    ProductEntity productEntity = ProductEntity.builder()
            .id(1)
            .producer(producerEntity)
            .name("product")
            .build();

    Product product = Product.builder()
            .id(1)
            .producer(producer)
            .name("product")
            .build();

    ReviewEntity reviewEntity = ReviewEntity.builder()
            .id(1)
            .product(productEntity)
            .user(userEntity)
            .build();

    Review review = Review.builder()
            .id(1)
            .product(product)
            .user(user)
            .build();

    @Test
    void createReview() {
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(userRepoMock.findByUsernameIsLike("user")).thenReturn(userEntity);
        when(reviewRepoMock.findByProductIdAndUserId(1,1)).thenReturn(null);
        CreateReviewRequest request = CreateReviewRequest.builder()
                .date(new Date())
                .user("user")
                .product("product")
                .text("text")
                .stars(4)
                .build();
        CreateReviewResponse er = CreateReviewResponse.builder()
                .response("Product: product has new review from user: user")
                .build();
        CreateReviewResponse ar = reviewService.createReview(request);
        assertEquals(er,ar);
    }

    @Test
    void deleteReview() {
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(userRepoMock.findByUsernameIsLike("user")).thenReturn(userEntity);
        when(reviewRepoMock.findByProductIdAndUserId(1,1)).thenReturn(reviewEntity);
        DeleteReviewRequest request = DeleteReviewRequest.builder()
                .product("product")
                .user("user")
                .build();
        DeleteReviewResponse er = DeleteReviewResponse.builder()
                .response("Review from user: user has been deleted from product: product")
                .build();
        DeleteReviewResponse ar = reviewService.deleteReview(request);
        assertEquals(er,ar);
    }

    @Test
    void getAllProductReviews() {
        when(productRepoMock.findByNameIsLike("product")).thenReturn(productEntity);
        when(reviewRepoMock.findByProductId(1)).thenReturn(List.of(reviewEntity));
        GetAllProductReviewsRequest request = GetAllProductReviewsRequest.builder()
                .product("product")
                .build();
        GetAllProductReviewsResponse er = GetAllProductReviewsResponse.builder()
                .reviews(List.of(review))
                .build();
        GetAllProductReviewsResponse ar = reviewService.getAllProductReviews(request);
        assertEquals(er,ar);
    }
}