package ru.otus.spring05.Exceptions;

public class BookExistException extends Exception {

    public BookExistException(String message) {
        super(message);
    }
}
