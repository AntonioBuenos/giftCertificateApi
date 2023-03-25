package by.smirnov.giftcertapi.exception;

import by.smirnov.giftcertapi.util.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    private static final String ERROR_KEY = "Error Message";

    @ExceptionHandler({NoSuchEntityException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEntityNotFountException(Exception e) {
        return getResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(Exception e) {
        return getResponse(e, HttpStatus.BAD_REQUEST);
    }

/*    @ExceptionHandler({NotModifiedException.class})
    public ResponseEntity<Object> handleNotModifiedException(Exception e) {
        return getResponse(e, HttpStatus.NOT_MODIFIED);
    }*/
/*

    @ExceptionHandler({AccessForbiddenException.class})
    public ResponseEntity<Object> handleAccessForbiddenException(Exception e) {
        return getResponse(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialsException(Exception e) {
        return getResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<Object> handleWrongNumberFormatException(Exception e) {
        return getResponse(e, HttpStatus.NOT_FOUND);
    }
*/

    private ErrorContainer getErrorContainer(Exception e){
        return ErrorContainer
                .builder()
                .exceptionId(UUIDGenerator.generateUUID())
                .errorMessage(e.getMessage())
                .e(e.getClass().toString())
                .time(LocalDateTime.now().toString())
                .build();
    }

    private ResponseEntity<Object> getResponse(Exception e, HttpStatus status){
        log.error(e.getMessage());
        return new ResponseEntity<>(Collections.singletonMap(
                ERROR_KEY, getErrorContainer(e)),
                status);
    }
}
