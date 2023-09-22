package com.example.subclub.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicationError {
    private int status;
    private String message;
    private Date date;

    public ApplicationError(int status, String message) {
        this.status = status;
        this.message = message;
        this.date = new Date();
    }
}
