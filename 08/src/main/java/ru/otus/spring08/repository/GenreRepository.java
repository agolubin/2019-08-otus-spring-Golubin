package ru.otus.spring08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring08.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {

    List<Genre> findAll();
    Optional<Genre> findGenreByName(String name);
}
