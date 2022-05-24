package de.emaarco.bpmnandai.credit.domain.model

import de.emaarco.bpmnandai.shared.annotations.Default

data class LoanRequest @Default constructor(
    val id: String,
    val applicant: Applicant,
    val creditAmount: Double,
    var creditworthy: String?,
) {

    constructor(id: String, newLoanRequest: NewLoanRequest) : this(
        id, newLoanRequest.applicant, newLoanRequest.creditAmount, null
    )

    fun updateResultOfCreditworthinessCheck(creditworthy: Boolean) {
        this.creditworthy = if (creditworthy) "YES" else "NO"
    }

    fun getCreditworthiness(): String {
        return creditworthy.let { "NO" }
    }

}

