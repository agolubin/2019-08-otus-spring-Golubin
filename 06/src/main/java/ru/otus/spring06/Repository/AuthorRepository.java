package ru.otus.spring06.Repository;

import ru.otus.spring06.Exceptions.AuthorExistException;
import ru.otus.spring06.domain.Author;

import java.util.List;

public interface AuthorRepository {

    void insert(Author author) throws AuthorExistException;
    void update(Author author) throws AuthorExistException;
    void delete(Author author) throws AuthorExistException;
    Author getByID(Long authorID);
    List<Author> findAll();
    Author getAuthorByName(String name, String surName);
}
