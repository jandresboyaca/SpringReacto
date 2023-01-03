package com.example.reactor.exceptions;

public class MyCustomException extends RuntimeException {
    public MyCustomException(String s) {
        super(s);
    }
}
