package ru.otus.spring09.exceptions;

public class AuthorExistException extends Exception {

    public AuthorExistException(String message) {
        super(message);
    }
}
