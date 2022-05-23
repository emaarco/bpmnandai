package de.emaarco.bpmnandai.credit.api.transport

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Information to add a new loan request")
data class NewLoanRequestTO(
    val applicant: ApplicantTO,
    val creditAmount: Double,
)