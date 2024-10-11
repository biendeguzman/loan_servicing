package com.example.loan.servicing.demo.service;

import com.example.loan.servicing.demo.model.InterestRateModel;
import com.example.loan.servicing.demo.repository.InterestRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestRateService {

    @Autowired
    private InterestRateRepository interestRateRepository;

    public InterestRateModel createInterestRate(InterestRateModel interestRate) {
        return interestRateRepository.save(interestRate);
    }

    public List<InterestRateModel> getAllInterestRates() {
        return interestRateRepository.findAll();
    }

    public List<InterestRateModel> getInterestRatesByLoanType(String loanType) {
        return interestRateRepository.findByLoanType(loanType);
    }

    public List<InterestRateModel> getInterestRatesByBankName(String bankName) {
        return interestRateRepository.findByBankName(bankName);
    }
}
