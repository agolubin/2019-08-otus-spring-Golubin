package ru.otus.spring05.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Genre {

    private Long genreID;
    private final String name;

    public Genre(Long genreID, String name) {
        this.genreID = genreID;
        this.name    = name;
    }
}
