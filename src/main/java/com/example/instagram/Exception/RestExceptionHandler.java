package com.example.instagram.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value = UsernameTaken.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handle(UsernameTaken ex, WebRequest req)
    {
        return new ErrorResponse("Username Already Taken");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handle(Exception ex, WebRequest req)
    {
        return new ErrorResponse("Issue not handled internally");
    }
}
