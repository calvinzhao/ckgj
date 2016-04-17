package com.ckgj.services.statement;

import com.ckgj.models.statement.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRepository extends JpaRepository<Statement, Long>  {


}

