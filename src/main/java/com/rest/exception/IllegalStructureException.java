package com.rest.exception;

public class IllegalStructureException extends RuntimeException{
    public IllegalStructureException(String message) {
        super(message);
        System.out.println(message);
    }
    public IllegalStructureException() {

    }
}
