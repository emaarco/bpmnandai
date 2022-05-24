package de.emaarco.bpmnandai.credit.infrastructure.entity

import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "loan_request_")
data class LoanRequestEntity(

    @Id
    @Column(name = "id_", unique = true, nullable = false, updatable = false)
    val id: String,

    @Embedded
    val applicant: ApplicantEntity,

    @Column(name = "credit_amount_", nullable = false)
    val creditAmount: Double,

    @Column(name = "creditworthy_", nullable = true)
    val creditworthy: String?

)