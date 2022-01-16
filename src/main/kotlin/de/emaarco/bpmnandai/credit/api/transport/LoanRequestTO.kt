package de.emaarco.bpmnandai.credit.api.transport

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Information to a specific loan request")
data class LoanRequestTO(
    val id: String,
    val applicant: ApplicantTO,
    val creditAmount: Double,
    var creditworthy: String,
)
