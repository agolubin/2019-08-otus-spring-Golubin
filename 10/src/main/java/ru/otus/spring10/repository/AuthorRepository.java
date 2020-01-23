package ru.otus.spring10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring10.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByNameAndSurName(String name, String surName);
}
