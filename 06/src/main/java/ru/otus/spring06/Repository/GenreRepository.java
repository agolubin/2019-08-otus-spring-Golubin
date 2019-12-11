package ru.otus.spring06.Repository;

import ru.otus.spring06.Exceptions.GenreExistException;
import ru.otus.spring06.domain.Genre;

import java.util.List;

public interface GenreRepository {

    void insert(Genre genre) throws GenreExistException;
    void update(Genre genre) throws GenreExistException;
    void delete(Genre genre) throws GenreExistException;
    Genre getByID(Long GenreID);
    List<Genre> findAll();
    Genre getGenreByName(String name);
}
