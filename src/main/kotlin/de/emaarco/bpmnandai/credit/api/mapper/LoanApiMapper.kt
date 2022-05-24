package de.emaarco.bpmnandai.credit.api.mapper

import de.emaarco.bpmnandai.credit.api.transport.LoanRequestTO
import de.emaarco.bpmnandai.credit.api.transport.NewLoanRequestTO
import de.emaarco.bpmnandai.credit.domain.model.LoanRequest
import de.emaarco.bpmnandai.credit.domain.model.NewLoanRequest
import org.mapstruct.Mapper

@Mapper
interface LoanApiMapper {

    fun mapToTO(model: LoanRequest): LoanRequestTO

    fun mapToModel(to: NewLoanRequestTO): NewLoanRequest

}