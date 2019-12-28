package ru.otus.spring08.exceptions;

public class CommentsExistException extends Exception {

    public CommentsExistException(String message) {
        super(message);
    }
}
