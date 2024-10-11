package com.example.loan.servicing.demo.controller;

import com.example.loan.servicing.demo.model.*;
import com.example.loan.servicing.demo.repository.ApprovalRepository;
import com.example.loan.servicing.demo.repository.CollateralRepository;
import com.example.loan.servicing.demo.repository.PersonalInfoRepository;
import com.example.loan.servicing.demo.service.DmnService;
import com.example.loan.servicing.demo.service.InterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class DmnController {
    @Autowired
    private DmnService dmnService;

    @Autowired
    private CollateralRepository collateralRepository;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private InterestRateService interestRateService;

    @PostMapping
    public ResponseEntity<LoanModel> createLoanApplication(@RequestBody LoanModel loanModel) {
        LoanModel createdLoanModel = dmnService.createLoanApplication(loanModel);
        return ResponseEntity.ok(createdLoanModel);
    }

    @PostMapping("/evaluate")
    public ResponseEntity<String> evaluateLoanDecision(
            @RequestParam String decisionId,
            @RequestBody LoanEvaluationRequest loanEvaluationRequest) {

        // Extract models from the request
        LoanModel loanModel = loanEvaluationRequest.getLoanModel();
        PersonalInfoModel personalInfoModel = loanEvaluationRequest.getPersonalInfoModel();
        CollateralModel collateralModel = loanEvaluationRequest.getCollateralModel();
        ApprovalModel approvalModel = loanEvaluationRequest.getApprovalModel();

        // Save personal info, collateral, and approval to respective repositories
        collateralRepository.save(collateralModel);
        personalInfoRepository.save(personalInfoModel);
        approvalRepository.save(approvalModel);

        // Evaluate loan decision using DMN service
        String decisionResult = dmnService.evaluateLoanDecision(decisionId, loanModel, personalInfoModel);
        return ResponseEntity.ok(decisionResult);
    }

    @PostMapping("/personal-info")
    public ResponseEntity<PersonalInfoModel> createPersonalInfo(@RequestBody PersonalInfoModel personalInfoModel) {
        PersonalInfoModel createdPersonalInfo = dmnService.savePersonalInfo(personalInfoModel);
        return ResponseEntity.ok(createdPersonalInfo);
    }

    @PostMapping("/collateral")
    public ResponseEntity<CollateralModel> createCollateral(@RequestBody CollateralModel collateralModel) {
        CollateralModel createdCollateral = dmnService.saveCollateral(collateralModel);
        return ResponseEntity.ok(createdCollateral);
    }

    @PostMapping("/approval")
    public ResponseEntity<ApprovalModel> createApproval(@RequestBody ApprovalModel approvalModel) {
        ApprovalModel createdApproval = dmnService.saveApproval(approvalModel);
        return ResponseEntity.ok(createdApproval);
    }

    @PostMapping("/evaluate-approval")
    public ResponseEntity<String> evaluateApproval(@RequestParam String loanRelease) {
        String approvalStatus = dmnService.evaluateApprovalStatus(loanRelease);
        return ResponseEntity.ok(approvalStatus);
    }

    @PostMapping("/interest-rate") // Specify a unique path for this method
    public ResponseEntity<InterestRateModel> createInterestRate(@RequestBody InterestRateModel interestRate) {
        InterestRateModel createdInterestRate = interestRateService.createInterestRate(interestRate);
        return ResponseEntity.ok(createdInterestRate);
    }

    @GetMapping("/by-loan-type")
    public ResponseEntity<List<InterestRateModel>> getInterestRatesByLoanType(@RequestParam String loanType) {
        List<InterestRateModel> interestRates = interestRateService.getInterestRatesByLoanType(loanType);
        return ResponseEntity.ok(interestRates);
    }

    @GetMapping("/by-bank-name")
    public ResponseEntity<List<InterestRateModel>> getInterestRatesByBankName(@RequestParam String bankName) {
        List<InterestRateModel> interestRates = interestRateService.getInterestRatesByBankName(bankName);
        return ResponseEntity.ok(interestRates);
    }
}
