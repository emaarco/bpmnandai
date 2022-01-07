package de.emaarco.bpmnandai

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class BpmnAndAiApplication

fun main(args: Array<String>) {
    runApplication<BpmnAndAiApplication>(*args)
}
