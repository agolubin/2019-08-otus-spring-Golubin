package ru.otus.spring09.exceptions;

public class GenreExistException extends Exception {

    public GenreExistException(String message) {
        super(message);
    }
}
