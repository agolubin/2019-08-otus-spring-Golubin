package ru.otus.spring06.exceptions;

public class BookExistException extends Exception {

    public BookExistException(String message) {
        super(message);
    }
}
