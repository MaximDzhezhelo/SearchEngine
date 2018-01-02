package com.kiev.makson.searchengineportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    public static final String BAD_REQUEST = "We are sorry but your request contains bad syntax and cannot be fulfilled";

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String handleIOException(Exception e) {
        System.out.println(e.toString());
        return BAD_REQUEST;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String handleException(Exception e) {
        System.out.println(e.toString());
        return BAD_REQUEST;
    }
}
