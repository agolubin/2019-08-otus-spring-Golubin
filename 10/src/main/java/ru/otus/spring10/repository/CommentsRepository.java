package ru.otus.spring10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring10.domain.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
