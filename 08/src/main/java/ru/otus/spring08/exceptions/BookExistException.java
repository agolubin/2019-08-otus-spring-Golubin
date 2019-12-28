package ru.otus.spring08.exceptions;

public class BookExistException extends Exception {

    public BookExistException(String message) {
        super(message);
    }
}
