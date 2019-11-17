package ru.otus.spring05.dao;

import ru.otus.spring05.domain.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDao {

    Genre insert(Genre genre) throws SQLException;
    void update(Genre genre);
    void deleteByID(int genreID);
    int checkByID(int genreID);
    List findAll();
    Genre getByID(int genreID);
    Genre getGenreByGenre(Genre genre);
    int countByName(Genre genre);
}
