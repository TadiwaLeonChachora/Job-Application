package com.example.jobapplication.reviews;

import com.example.jobapplication.company.Company;
import com.example.jobapplication.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepo reviewRepo;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepo reviewRepo, CompanyService companyService){
        this.reviewRepo = reviewRepo;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepo.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {

        Company company = companyService.getCompanyById(companyId);

        if(company != null){
            review.setCompany(company);
            reviewRepo.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {

        List<Review> reviews = reviewRepo.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);


      //return reviewRepo.findByCompanyIdAndId(companyId, reviewId);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {

        if(companyService.getCompanyById(companyId) != null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepo.save(updatedReview);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {

        if(companyService.getCompanyById(companyId) != null
        && reviewRepo.existsById(reviewId)){
            Review review = reviewRepo.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company, companyId);
            reviewRepo.deleteById(reviewId);
            return true;
        }else {
            return false;
        }
    }
}
