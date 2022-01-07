package de.emaarco.bpmnandai.project.infrastructure.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "bank_credit_approval")
class BankLoanRequestEntity(

    @Id
    @Column(name = "id", nullable = false)
    var requestId: String,

    @Column(name = "Gender")
    var gender: String? = null,

    @Column(name = "City_Category")
    val cityCategory: String? = null,

    @Column(name = "Employer_Category1")
    val employerCategory: String? = null,

    @Column(name = "Monthly_Income")
    val monthlyIncome: Double? = null,

    @Column(name = "Contacted")
    val contacted: String? = null,

    @Column(name = "Source_Category")
    val sourceCategory: String? = null,

    @Column(name = "Existing_EMI")
    val existingEmi: Double? = null,

    @Column(name = "Loan_Amount")
    val loanAmount: Int? = null,

    @Column(name = "Loan_Period")
    val loanPeriod: Int? = null,

    @Column(name = "Interest_Rate")
    val interestRate: Double? = null,

    @Column(name = "EMI")
    val emi: Int? = null,

    @Column(name = "is_approved")
    val isApproved: String? = null,

    @Column(name = "prediction_is_approved")
    var predictionIsApproved: String? = null,

    @Column(name = "prediction_is_approved_2")
    var predictionIsApproved2: String? = null

)