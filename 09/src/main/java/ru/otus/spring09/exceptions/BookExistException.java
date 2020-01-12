package ru.otus.spring09.exceptions;

public class BookExistException extends Exception {

    public BookExistException(String message) {
        super(message);
    }
}
