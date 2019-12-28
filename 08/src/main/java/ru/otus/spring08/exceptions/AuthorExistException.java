package ru.otus.spring08.exceptions;

public class AuthorExistException extends Exception {

    public AuthorExistException(String message) {
        super(message);
    }
}
