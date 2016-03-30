package com.ckgj.services.company;

import com.ckgj.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>  {
}

