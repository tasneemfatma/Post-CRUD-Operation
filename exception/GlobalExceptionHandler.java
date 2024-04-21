package com.crud.crud.operation.exception;


import com.crud.crud.operation.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest webRequest){
        Timestamp timestamp = new Timestamp(new Date().getTime()); // Convert Date to Timestamp
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(timestamp)
                .message(ex.getMessage())
                .description(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);


    }

}
