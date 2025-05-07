package com.hitruong.RestAPI.exception;

import com.hitruong.RestAPI.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ErrorResponse> handleVehicleServiceException(CustomException ex) {
        return new ResponseEntity<>(
          new ErrorResponse().builder()
                  .message(ex.getMessage())
                  .code(ex.getError())
                  .build(),
                HttpStatus.NOT_FOUND
        );
    }
}
