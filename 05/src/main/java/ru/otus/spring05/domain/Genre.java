package ru.otus.spring05.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Genre {

    private int genreID;
    private final String name;

    public Genre(int genreID, String name) {
        this.genreID = genreID;
        this.name    = name;
    }
}
