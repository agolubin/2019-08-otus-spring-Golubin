package ru.otus.spring05.dao;

import ru.otus.spring05.Exceptions.GenreExistException;
import ru.otus.spring05.domain.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDao {

    Genre insert(Genre genre) throws GenreExistException;
    void update(Genre genre);
    void deleteByID(Long genreID);
    int countByID(Long genreID);
    List<Genre> findAll();
    Genre getGenreByName(String name);
    int countByName(String name);
}
