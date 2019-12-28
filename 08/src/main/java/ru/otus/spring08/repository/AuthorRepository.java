package ru.otus.spring08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring08.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findAll();
    Optional<Author> findAuthorByNameAndSurName(String name, String surName);
}
