package com.example.jobapplication.reviews;

import com.example.jobapplication.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {


    List<Review> findByCompanyId(Long id);
    Review findByCompanyIdAndId(Long companyId, Long id);

}
