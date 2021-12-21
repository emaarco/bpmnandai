package de.hsa.bpmnandai.project.infrastructure.repository;

import de.hsa.bpmnandai.project.infrastructure.BankLoanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankLoanRequestRepository extends JpaRepository<BankLoanRequestEntity, String> {
}
