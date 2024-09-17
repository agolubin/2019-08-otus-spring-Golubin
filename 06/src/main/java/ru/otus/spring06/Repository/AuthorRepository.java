package ru.otus.spring06.repository;

import ru.otus.spring06.exceptions.AuthorExistException;
import ru.otus.spring06.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    void insert(Author author) throws AuthorExistException;
    void update(Author author) throws AuthorExistException;
    void delete(Author author) throws AuthorExistException;
    Optional<Author> getByID(Long authorID);
    List<Author> findAll();
    Author getAuthorByName(String name, String surName);
}
