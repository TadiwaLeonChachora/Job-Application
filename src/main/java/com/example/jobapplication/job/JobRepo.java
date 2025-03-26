package com.example.jobapplication.job;

import com.example.jobapplication.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepo extends JpaRepository<Job, Long > {
}
