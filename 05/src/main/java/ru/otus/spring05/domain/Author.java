package ru.otus.spring05.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {

    private int authorID;
    private final String name;
    private final String surName;

    public Author(int authorID, String name, String surName) {
        this.authorID = authorID;
        this.name     = name;
        this.surName  = surName;
    }
}
