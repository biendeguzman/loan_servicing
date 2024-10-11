package com.example.loan.servicing.demo.repository;

import com.example.loan.servicing.demo.model.InterestRateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRateRepository extends JpaRepository<InterestRateModel, Integer> {
    List<InterestRateModel> findByLoanType(String loanType);
    List<InterestRateModel> findByBankName(String bankName);
}
