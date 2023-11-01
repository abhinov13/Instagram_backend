package com.example.instagram.Exception;
import java.util.Date;

public class ErrorResponse {
    String message;
    Date errorDate;

    public ErrorResponse()
    {
        this.errorDate = new Date();
    }

    public ErrorResponse(String message) {
        this.message = message;
        this.errorDate = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
