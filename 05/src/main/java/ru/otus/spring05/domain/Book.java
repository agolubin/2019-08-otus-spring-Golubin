package ru.otus.spring05.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {

    private Long  bookID;
    private final Author author;
    private final Genre genre;
    private final String name;

    public Book(Long bookID, Author author, Genre genre, String name) {
        this.bookID   = bookID;
        this.author   = author;
        this.genre    = genre;
        this.name     = name;

    }

}
