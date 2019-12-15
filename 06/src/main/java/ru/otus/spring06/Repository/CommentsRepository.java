package ru.otus.spring06.repository;

import ru.otus.spring06.exceptions.CommentsExistException;
import ru.otus.spring06.domain.Comments;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository {

    void insert(Comments comments) throws CommentsExistException;
    void update(Comments comments) throws CommentsExistException;
    void delete(Comments comments) throws CommentsExistException;
    Optional<Comments> getByID(Long commentsID);
    List<Comments> findAll();
}
