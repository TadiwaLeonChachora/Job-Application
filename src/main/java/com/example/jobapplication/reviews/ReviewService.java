package com.example.jobapplication.reviews;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long id);

    boolean addReview(Long companyId, Review review);
    Review getReviewById(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}
