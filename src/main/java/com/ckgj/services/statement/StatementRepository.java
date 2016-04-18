package com.ckgj.services.statement;

import com.ckgj.models.company.Company;
import com.ckgj.models.statement.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface StatementRepository extends JpaRepository<Statement, Long>  {
    Optional<Statement> findOneByCompanyAndPeriod (Company company, String period);

    Iterable<Statement> findAllByCompany (Company company);
}

