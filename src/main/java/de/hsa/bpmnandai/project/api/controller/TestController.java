package de.hsa.bpmnandai.project.api.controller;

import de.hsa.bpmnandai.project.domain.facade.ExternalDmnFacade;
import de.hsa.bpmnandai.project.domain.facade.SimpleDmnFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {

    private final SimpleDmnFacade simpleDmnFacade;
    private final ExternalDmnFacade externalDmnFacade;

    @PostMapping("/simple-dmn")
    public ResponseEntity<Void> processRequestsWithDecisionTable() {
        log.debug("Request request to perform something");
        simpleDmnFacade.processLoanRequests();
        simpleDmnFacade.addHistory();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/external-dmn")
    public ResponseEntity<Void> processRequestsWithPmmlDMN() {
        log.debug("Request request to perform something");
        externalDmnFacade.processLoanRequests();
        return ResponseEntity.ok().build();
    }

}
