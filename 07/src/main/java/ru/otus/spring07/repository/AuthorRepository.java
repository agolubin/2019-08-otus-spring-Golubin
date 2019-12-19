package ru.otus.spring07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring07.exceptions.AuthorExistException;
import ru.otus.spring07.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByNameAndSurName(String name, String surName);
}
