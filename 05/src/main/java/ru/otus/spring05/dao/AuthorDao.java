package ru.otus.spring05.dao;

import ru.otus.spring05.domain.Author;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AuthorDao {

    Author insert(Author author) throws SQLException;
    void update(Author author);
    //void delete(int authorID);
    void deleteByID(int authorID);
    int checkByID(int authorID);
    List findAll();
    Author getByID(int authorID);
    Author getAuthorByAuthor(Author author);
    int countByName(Author author);
}
