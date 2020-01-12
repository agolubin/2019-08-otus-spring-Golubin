package ru.otus.spring09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring09.domain.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
