package ru.otus.spring07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring07.domain.Author;
import ru.otus.spring07.exceptions.GenreExistException;
import ru.otus.spring07.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre save(Genre genre);
    void delete(Genre genre);
    Optional<Genre> findById(Long genreID);
    List<Genre> findAll();
    Genre findGenreByName(String name);
}
