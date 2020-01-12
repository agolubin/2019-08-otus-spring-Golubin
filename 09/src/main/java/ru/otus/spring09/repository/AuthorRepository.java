package ru.otus.spring09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring09.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByNameAndSurName(String name, String surName);
}
