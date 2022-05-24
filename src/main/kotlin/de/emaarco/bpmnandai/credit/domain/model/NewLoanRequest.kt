package de.emaarco.bpmnandai.credit.domain.model

data class NewLoanRequest(
    val applicant: Applicant,
    val creditAmount: Double,
)
