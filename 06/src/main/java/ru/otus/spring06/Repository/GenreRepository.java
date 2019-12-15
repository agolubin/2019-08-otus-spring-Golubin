package ru.otus.spring06.repository;

import ru.otus.spring06.exceptions.GenreExistException;
import ru.otus.spring06.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    void insert(Genre genre) throws GenreExistException;
    void update(Genre genre) throws GenreExistException;
    void delete(Genre genre) throws GenreExistException;
    Optional<Genre> getByID(Long GenreID);
    List<Genre> findAll();
    Genre getGenreByName(String name);
}
