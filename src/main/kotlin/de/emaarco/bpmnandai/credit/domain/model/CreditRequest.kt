package de.emaarco.bpmnandai.credit.domain.model

data class CreditRequest(
    val requestId: String,
    var gender: String?,
    val cityCategory: String?,
    val employerCategory: String?,
    val monthlyIncome: Double?,
    val contacted: String?,
    val sourceCategory: String?,
    val existingEmi: Double?,
    val loanAmount: Int?,
    val loanPeriod: Int?,
    val interestRate: Double?,
    val emi: Int? = null,
    val isApproved: String?,
    var predictionIsApproved: String?,
    var predictionIsApproved2: String?,
)