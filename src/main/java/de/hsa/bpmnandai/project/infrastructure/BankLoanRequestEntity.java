package de.hsa.bpmnandai.project.infrastructure;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.print.attribute.standard.MediaSize;

@Builder
@NoArgsConstructor
@Entity(name = "bank_marketing_leads")
public class BankLoanRequestEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

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

    @Column(name = "EMI")
    private Integer emi;

    @Column(name = "is_approved")
    private String isApproved;

    @Column(name = "prediction_is_approved")
    private String predictionIsApproved;

}

