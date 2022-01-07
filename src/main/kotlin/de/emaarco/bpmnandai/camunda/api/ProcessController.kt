package de.emaarco.bpmnandai.camunda.api

import de.emaarco.bpmnandai.camunda.domain.ProcessService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/process")
class ProcessController(private val processService: ProcessService) {

    val log = KotlinLogging.logger {}

    @GetMapping("/tasks")
    fun getAllTasks(): ResponseEntity<List<String>> {
        log.debug { "Received request to get all open camunda-tasks" }
        return ResponseEntity.ok().body(processService.getAllTasks().map { task -> task.id })
    }

    @PostMapping("/task/{taskId}")
    fun finishProcessTask(@PathVariable("taskId") taskId: String): ResponseEntity<Void> {
        log.debug { "Received request to finish task '$taskId'" }
        processService.finishTask(taskId)
        return ResponseEntity.ok().build()
    }

}