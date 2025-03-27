package com.example.jobapplication.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean deleteCompany(Long id);
}
