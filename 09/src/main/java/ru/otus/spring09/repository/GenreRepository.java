package ru.otus.spring09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring09.domain.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findGenreByName(String name);
}
