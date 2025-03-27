package com.example.jobapplication.company;

import com.example.jobapplication.job.Job;
import org.springframework.stereotype.Service;

import java.nio.charset.CoderMalfunctionError;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepo companyRepo;

    public CompanyServiceImpl(CompanyRepo companyRepo){
        this.companyRepo = companyRepo;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepo.findById(id).orElse(null);

    }

    @Override
    public boolean updateCompany(Company updatedCompany, Long id) {
        Optional<Company> optionalCompany = companyRepo.findById(id);

        if(optionalCompany.isPresent()){
            Company company = optionalCompany.get();
            company.setDescription(updatedCompany.getDescription());
            company.setJobs(updatedCompany.getJobs());
            company.setName(updatedCompany.getName());
            companyRepo.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepo.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {

        if(companyRepo.existsById(id)){
            companyRepo.deleteById(id);
            return true;
        }else{
            return false;
        }

    }
}
