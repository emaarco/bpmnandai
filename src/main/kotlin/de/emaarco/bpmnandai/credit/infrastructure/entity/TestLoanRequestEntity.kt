package de.emaarco.bpmnandai.credit.infrastructure.entity

import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "loan_request_test_data_")
data class TestLoanRequestEntity(

    @Id
    @Column(name = "id_", unique = true, nullable = false, updatable = false)
    val id: String,

    @Embedded
    val applicant: ApplicantEntity,

    @Column(name = "credit_amount_", nullable = false)
    val creditAmount: Double,

    @Column(name = "creditworthy_", nullable = false)
    val creditworthy: String

)