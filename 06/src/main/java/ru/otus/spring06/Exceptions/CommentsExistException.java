package ru.otus.spring06.exceptions;

public class CommentsExistException extends Exception {

    public CommentsExistException(String message) {
        super(message);
    }
}
