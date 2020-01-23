package ru.otus.spring10.exceptions;

public class BookExistException extends Exception {

    public BookExistException(String message) {
        super(message);
    }
}
