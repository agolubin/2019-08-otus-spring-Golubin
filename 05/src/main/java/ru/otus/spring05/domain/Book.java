package ru.otus.spring05.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {

    private int bookID;
    private final int authorID;
    private final int genreID;
    private final String name;

    private final String authorName;
    private final String authorSurName;
    private final String genreName;

    public Book(int bookID, int authorID, int genreID, String name) {
        this.bookID   = bookID;
        this.authorID = authorID;
        this.genreID  = genreID;
        this.name     = name;
        this.authorName = "";
        this.authorSurName = "";
        this.genreName = "";
    }

    public Book(int bookID, String authorName, String authorSurName, String genreName, String name) {
        this.bookID   = bookID;
        this.authorID = 0;
        this.genreID  = 0;
        this.name     = name;
        this.authorName = authorName;
        this.authorSurName = authorSurName;
        this.genreName = genreName;
    }

}
