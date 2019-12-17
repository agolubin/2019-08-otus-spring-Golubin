package ru.otus.spring07.exceptions;

public class BookExistException extends Exception {

    public BookExistException(String message) {
        super(message);
    }
}
