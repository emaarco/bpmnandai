package de.emaarco.bpmnandai.credit.domain.model

data class LoanRequest(
    val id: String,
    val applicant: Applicant,
    val creditAmount: Double,
    var creditworthy: String,
)
