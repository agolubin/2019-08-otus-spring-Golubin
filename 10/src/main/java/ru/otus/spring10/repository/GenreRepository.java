package ru.otus.spring10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring10.domain.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findGenreByName(String name);
}
