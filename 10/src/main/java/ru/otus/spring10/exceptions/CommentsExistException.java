package ru.otus.spring10.exceptions;

public class CommentsExistException extends Exception {

    public CommentsExistException(String message) {
        super(message);
    }
}
