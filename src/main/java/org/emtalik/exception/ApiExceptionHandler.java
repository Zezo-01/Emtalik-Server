package org.emtalik.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ApiExceptionHandler{

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
       ApiException apiException = new ApiException(
           e.getMessage(), 
           e.getHttpStatus(), 
           ZonedDateTime.now(ZoneId.of("Z")));
           return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
}