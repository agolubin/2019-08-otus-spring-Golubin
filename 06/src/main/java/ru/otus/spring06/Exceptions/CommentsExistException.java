package ru.otus.spring06.Exceptions;

public class CommentsExistException extends Exception {

    public CommentsExistException(String message) {
        super(message);
    }
}
