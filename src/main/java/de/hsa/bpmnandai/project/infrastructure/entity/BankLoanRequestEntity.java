package de.hsa.bpmnandai.project.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bank_credit_approval")
public class BankLoanRequestEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "City_Category")
    private String cityCategory;

    @Column(name = "Employer_Category1")
    private String employerCategory;

    @Column(name = "Monthly_Income")
    private Double monthlyIncome;

    @Column(name = "Contacted")
    private String contacted;

    @Column(name = "Source_Category")
    private String sourceCategory;

    @Column(name = "Existing_EMI")
    private Double existingEmi;

    @Column(name = "Loan_Amount")
    private Integer loanAmount;

    @Column(name = "Loan_Period")
    private Integer loanPeriod;

    @Column(name = "Interest_Rate")
    private Double interestRate;

    @Column(name = "EMI")
    private Integer emi;

    @Column(name = "is_approved")
    private String isApproved;

    @Column(name = "prediction_is_approved")
    private String predictionIsApproved;

    public void setPredictionIsApproved(final String predictionIsApproved) {
        this.predictionIsApproved = predictionIsApproved;
    }
}

