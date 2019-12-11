package ru.otus.spring06.Repository;

import ru.otus.spring06.Exceptions.CommentsExistException;
import ru.otus.spring06.domain.Comments;

import java.util.List;

public interface CommentsRepository {

    void insert(Comments comments) throws CommentsExistException;
    void update(Comments comments) throws CommentsExistException;
    void delete(Comments comments) throws CommentsExistException;
    Comments getByID(Long commentsID);
    List<Comments> findAll();
}
