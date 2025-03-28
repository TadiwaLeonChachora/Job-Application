package com.example.jobapplication.reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    // GET /companies/{companyId}/reviews
    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){

        List<Review> reviews = reviewService.getAllReviews(companyId);

        if(reviews.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }

    }

    // POST /companies/{companyId}/reviews
    @PostMapping()
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){

        boolean isReviewSaved =  reviewService.addReview(companyId, review);

        if(isReviewSaved){
            return new ResponseEntity<>("Review has been added!", HttpStatus.CREATED);
        }else {
        return new ResponseEntity<>("Failed to add", HttpStatus.NOT_FOUND);
        }
    }

    // GET /companies/{companyId}/reviews/{reviewId}
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId ){

        Review review = reviewService.getReviewById(companyId, reviewId);

        if(review != null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT /companies/{companyId}/reviews/{reviewId}
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId, @RequestBody Review review){

        boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);

        if(isReviewUpdated){
            return new ResponseEntity<>("Review has been updated!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not updated!", HttpStatus.NOT_FOUND);
        }

    }

    // DELETE /companies/{companyId}/reviews/{reviewId}
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId){

        boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);

        if(isReviewDeleted){
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
        }
    }





}
