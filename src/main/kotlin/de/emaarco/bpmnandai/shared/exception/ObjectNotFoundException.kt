package de.emaarco.bpmnandai.shared.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * This exception should be thrown if a requested object could not be found (e.g. in the database)
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ObjectNotFoundException(message: String?) : RuntimeException(message)