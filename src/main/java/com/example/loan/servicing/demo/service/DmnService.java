package com.example.loan.servicing.demo.service;

import com.example.loan.servicing.demo.model.*;
import com.example.loan.servicing.demo.repository.ApprovalRepository;
import com.example.loan.servicing.demo.repository.CollateralRepository;
import com.example.loan.servicing.demo.repository.LoanApplicationRepository;
import com.example.loan.servicing.demo.repository.PersonalInfoRepository;
import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DmnService {
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    @Autowired
    private CollateralRepository collateralRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private ZeebeClient zeebeClient;

    @Autowired
    private InterestRateService interestRateService;

    private final RestTemplate restTemplate = new RestTemplate();

    public LoanModel createLoanApplication(LoanModel loanModel) {
        return loanApplicationRepository.save(loanModel);
    }

    public PersonalInfoModel savePersonalInfo(PersonalInfoModel personalInfoModel) {
        return personalInfoRepository.save(personalInfoModel);
    }

    public CollateralModel saveCollateral(CollateralModel collateralModel) {
        return collateralRepository.save(collateralModel);
    }

    public ApprovalModel saveApproval(ApprovalModel approvalModel) {
        return approvalRepository.save(approvalModel);
    }

    public List<LoanModel> getAllLoanApplications() {
        return loanApplicationRepository.findAll();
    }

    public String evaluateLoanDecision(String decisionId, LoanModel loanModel, PersonalInfoModel personalInfoModel) {
        try {
            // Fetch interest rates based on loanType and bankName
            List<InterestRateModel> interestRates = interestRateService.getInterestRatesByLoanType(loanModel.getLoanType());

            // Find matching bank
            InterestRateModel interestRateModel = interestRates.stream()
                    .filter(rate -> rate.getBankName().equals(loanModel.getBankName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No interest rate found for the bank and loan type"));

            double interestRate = interestRateModel.getInterestRate();
            String bankName = interestRateModel.getBankName();

            // Map the variables for DMN evaluation
            Map<String, Object> inputVariables = new HashMap<>();
            inputVariables.put("loanType", loanModel.getLoanType());
            inputVariables.put("customerIncome", loanModel.getCustomerIncome());
            inputVariables.put("maxLoanAmount", loanModel.getMaxLoanAmount());
            inputVariables.put("loanTerm", loanModel.getLoanTerm());
            inputVariables.put("interestRate", interestRate); // Include interest rate in the decision
            inputVariables.put("bankName", bankName);

            inputVariables.put("FullName", personalInfoModel.getFullName());
            inputVariables.put("PhoneNumber", personalInfoModel.getPhoneNumber());
            inputVariables.put("EmailAddress", personalInfoModel.getEmailAddress());
            inputVariables.put("TelephoneNumber", personalInfoModel.getTelephoneNumber());

            // Evaluate decision using Zeebe client
            var decisionResult = zeebeClient.newEvaluateDecisionCommand()
                    .decisionId(decisionId)
                    .variables(inputVariables)
                    .send()
                    .join()
                    .getDecisionOutput();

            // Include collateral in the response
            CollateralModel collateralModel = new CollateralModel(loanModel.getLoanType());
            String collateral = collateralModel.getCollateral();

            loanModel.setCollateral(collateral);
            loanApplicationRepository.save(loanModel);


            return decisionResult + ", Interest Rate: " + interestRate + "%, Collateral: " + collateral;

        } catch (Exception e) {
            return "Error evaluating loan decision: " + e.getMessage();
        }
    }

    public String evaluateApprovalStatus(String loanRelease) {
        ApprovalModel approvalModel = new ApprovalModel(loanRelease);
        return approvalModel.getApprovalStatus(); // Assume ApprovalModel has a method to get status
    }
}