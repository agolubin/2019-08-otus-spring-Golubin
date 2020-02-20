package ru.otus.spring05.Exceptions;

public class AuthorExistException extends Exception {

    public AuthorExistException(String message) {
        super(message);
    }
}
