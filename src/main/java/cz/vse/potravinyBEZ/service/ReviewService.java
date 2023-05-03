package cz.vse.potravinyBEZ.service;

import cz.vse.potravinyBEZ.domain.review.*;

public interface ReviewService {
    CreateReviewResponse createReview(CreateReviewRequest review);
    DeleteReviewResponse deleteReview(DeleteReviewRequest review);
    GetAllProductReviewsResponse getAllProductReviews(GetAllProductReviewsRequest request);
}
