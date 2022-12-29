package com.example.reactor;

public class MyCustomException extends RuntimeException {
    public MyCustomException(String s) {
        super(s);
    }
}
