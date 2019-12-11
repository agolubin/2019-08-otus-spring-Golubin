package ru.otus.spring06.Exceptions;

public class BookExistException extends Exception {

    public BookExistException(String message) {
        super(message);
    }
}
