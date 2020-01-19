package ru.otus.spring10.exceptions;

public class AuthorExistException extends Exception {

    public AuthorExistException(String message) {
        super(message);
    }
}
