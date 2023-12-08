package com.myblog2.controller;

import com.myblog2.exception.ResourceNotFound;
import com.myblog2.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleException(ResourceNotFound e, WebRequest webRequest){
        ErrorDetails er= new ErrorDetails(new Date(),e.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);


    }
}
