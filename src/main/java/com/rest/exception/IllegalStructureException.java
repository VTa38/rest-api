package com.rest.exception;

public class IllegalStructureException extends RuntimeException {
    //Исключение, которое выбрасывается в слачае неправильной структуры

    public IllegalStructureException(String message) {
        super(message);
        System.out.println(message);

    }

    public IllegalStructureException() {

    }
}
