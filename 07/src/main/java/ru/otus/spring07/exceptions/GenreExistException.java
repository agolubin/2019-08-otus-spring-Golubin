package ru.otus.spring07.exceptions;

public class GenreExistException extends Exception {

    public GenreExistException(String message) {
        super(message);
    }
}
