package ru.otus.spring07.exceptions;

public class AuthorExistException extends Exception {

    public AuthorExistException(String message) {
        super(message);
    }
}
