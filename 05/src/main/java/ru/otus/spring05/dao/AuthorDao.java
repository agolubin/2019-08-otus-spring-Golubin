package ru.otus.spring05.dao;

import ru.otus.spring05.Exceptions.AuthorExistException;
import ru.otus.spring05.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author insert(Author author) throws AuthorExistException;
    void update(Author author);
    void deleteByID(Long authorID);
    int checkByID(Long authorID);
    List<Author> findAll();
    Author getAuthorByName(String name, String surName);
    int countByName(String name, String surName);
}
