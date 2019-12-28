package ru.otus.spring08.exceptions;

public class GenreExistException extends Exception {

    public GenreExistException(String message) {
        super(message);
    }
}
