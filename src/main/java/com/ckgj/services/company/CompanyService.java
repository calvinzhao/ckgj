package com.ckgj.services.company;

import com.ckgj.models.company.Company;
import com.ckgj.models.company.CompanyForm;
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

    public void deleteCompany(Long id) {
        this.companyRepository.delete(id);
    }

    public Iterable<Company> findAll() {
        return this.companyRepository.findAll();
    }

    public Company createOrUpdate(CompanyForm companyForm) {
        Company company;
        //TODO: test
        if (companyForm.getId() > 0) {
            // update old one
            company = companyRepository.findOne(companyForm.getId());
        } else {
            // insert one
            company = new Company();
            company.setEmployeeCnt(0);
            company.setDateCreated(new Date());
        }
        company.setName(companyForm.getName());
        company.setInstitutionCode(companyForm.getInstitutionCode());
        return companyRepository.save(company);
    }

    public Company updateEmployeeCnt(Company company, int delta) {
        int cnt = company.getEmployeeCnt();
        cnt += delta;
        if (cnt < 0)
            cnt = 0;
        company.setEmployeeCnt(cnt);
        return companyRepository.save(company);
    }
}
