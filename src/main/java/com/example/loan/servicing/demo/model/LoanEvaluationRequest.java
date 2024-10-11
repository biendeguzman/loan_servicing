package com.example.loan.servicing.demo.model;

public class LoanEvaluationRequest {

    private LoanModel loanModel;
    private PersonalInfoModel personalInfoModel;
    private CollateralModel collateralModel;
    private ApprovalModel approvalModel;

    public LoanModel getLoanModel() {
        return loanModel;
    }

    public void setLoanModel(LoanModel loanModel) {
        this.loanModel = loanModel;
    }

    public PersonalInfoModel getPersonalInfoModel() {
        return personalInfoModel;
    }

    public void setPersonalInfoModel(PersonalInfoModel personalInfoModel) {
        this.personalInfoModel = personalInfoModel;
    }

    public CollateralModel getCollateralModel() {
        return collateralModel;
    }

    public void setCollateralModel(CollateralModel collateralModel) {
        this.collateralModel = collateralModel;
    }

    public ApprovalModel getApprovalModel() {
        return approvalModel;
    }

    public void setApprovalModel(ApprovalModel approvalModel) {
        this.approvalModel = approvalModel;
    }
}
