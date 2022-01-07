package de.emaarco.bpmnandai

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BpmnAndAiApplication {

    fun main(args: Array<String>) {
        runApplication<BpmnAndAiApplication>(*args)
    }
}
