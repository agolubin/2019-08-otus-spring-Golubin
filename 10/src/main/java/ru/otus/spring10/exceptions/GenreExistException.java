package ru.otus.spring10.exceptions;

public class GenreExistException extends Exception {

    public GenreExistException(String message) {
        super(message);
    }
}
