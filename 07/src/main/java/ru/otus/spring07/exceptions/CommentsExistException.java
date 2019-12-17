package ru.otus.spring07.exceptions;

public class CommentsExistException extends Exception {

    public CommentsExistException(String message) {
        super(message);
    }
}
