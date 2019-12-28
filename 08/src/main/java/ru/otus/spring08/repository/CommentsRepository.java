package ru.otus.spring08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring08.domain.Comments;

import java.util.List;

public interface CommentsRepository extends MongoRepository<Comments, String> {

    List<Comments> findAll();
}
