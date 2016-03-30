package com.ckgj.services.company;

import com.ckgj.models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompanyService {
    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {

        this.companyRepository = companyRepository;
    }

    public Company findCompany(Long id) {
        return this.companyRepository.getOne(id);
    }

    public void deleteMessage(Long id) {
        this.companyRepository.delete(id);
    }

    public Iterable<Company> findAll() {
        return this.companyRepository.findAll();
    }

    public Company create(Company company) {
        //TODO:
        company.setEmployeeCnt(0);
        company.setDateCreated(new Date());
        return companyRepository.save(company);
    }
}
