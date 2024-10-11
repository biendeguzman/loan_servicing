package com.example.loan.servicing.demo.repository;

import com.example.loan.servicing.demo.model.LoanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanModel, Long> {
}
