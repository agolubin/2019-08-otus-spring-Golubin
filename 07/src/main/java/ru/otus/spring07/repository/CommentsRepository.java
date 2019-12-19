package ru.otus.spring07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring07.domain.Genre;
import ru.otus.spring07.exceptions.CommentsExistException;
import ru.otus.spring07.domain.Comments;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
