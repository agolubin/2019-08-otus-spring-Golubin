package ru.otus.spring05.Exceptions;

public class GenreExistException extends Exception {

    public GenreExistException(String message) {
        super(message);
    }
}
