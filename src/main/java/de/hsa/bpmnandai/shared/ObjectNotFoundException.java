package de.hsa.bpmnandai.shared;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception should be thrown if a requested object could not be found (e.g. in the database)
 */
@Slf4j
@ResponseStatus(value = HttpStatus.NOT_FOUND)
final public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(final String message) {
        super(message);
        log.debug(message);
    }

}
