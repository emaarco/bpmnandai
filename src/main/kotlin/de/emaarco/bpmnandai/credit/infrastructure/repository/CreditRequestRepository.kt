package de.emaarco.bpmnandai.credit.infrastructure.repository

import de.emaarco.bpmnandai.credit.infrastructure.entity.CreditRequestEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CreditRequestRepository : JpaRepository<CreditRequestEntity, String> {

    fun findByRequestId(id: String): CreditRequestEntity?

}