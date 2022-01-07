package de.emaarco.bpmnandai.credit.infrastructure.repository

import de.emaarco.bpmnandai.credit.infrastructure.entity.BankLoanRequestEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BankLoanRequestRepository : JpaRepository<BankLoanRequestEntity, String> {

    fun findByRequestId(id: String): BankLoanRequestEntity?

}