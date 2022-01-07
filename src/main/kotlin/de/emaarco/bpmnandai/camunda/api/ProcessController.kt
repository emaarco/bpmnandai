package de.emaarco.bpmnandai.camunda.api

import de.emaarco.bpmnandai.camunda.domain.ProcessService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/process")
class ProcessController(private val processService: ProcessService) {

    @GetMapping("/tasks")
    fun getAllTasks(): ResponseEntity<List<String>> {
        println("Received request to get all open camunda-tasks")
        return ResponseEntity.ok().body(processService.getAllTasks().map { task -> task.id })
    }

    @PostMapping("/task/{taskId}")
    fun finishProcessTask(@PathVariable("taskId") taskId: String): ResponseEntity<Void> {
        processService.finishTask(taskId)
        return ResponseEntity.ok().build()
    }

}