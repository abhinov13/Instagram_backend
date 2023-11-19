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

    @ExceptionHandler(value = IncorrectUserOrPassword.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponse handle(IncorrectUserOrPassword ex, WebRequest req)
    {
        return new ErrorResponse("Username or Password is incorrect");
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handle(UserNotFoundException ex, WebRequest req)
    {
        return new ErrorResponse("Username not found");
    }

    @ExceptionHandler(value = InvalidFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handle(InvalidFileException ex, WebRequest req)
    {
        return new ErrorResponse("Invalid file exception");
    }

    @ExceptionHandler(value = PostNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handle(PostNotFoundException ex, WebRequest req)
    {
        return new ErrorResponse("Post not found");
    }

    @ExceptionHandler(value = CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handle(CommentNotFoundException ex, WebRequest req)
    {
        return new ErrorResponse("Comment not found");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handle(Exception ex, WebRequest req)
    {
        return new ErrorResponse("Issue not handled internally");
    }
}
