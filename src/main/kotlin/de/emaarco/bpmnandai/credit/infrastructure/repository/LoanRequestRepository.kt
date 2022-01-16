package de.emaarco.bpmnandai.credit.infrastructure.repository

import de.emaarco.bpmnandai.credit.infrastructure.entity.LoanRequestEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LoanRequestRepository : JpaRepository<LoanRequestEntity, String> {

    fun findFirstById(id: String): LoanRequestEntity?

}