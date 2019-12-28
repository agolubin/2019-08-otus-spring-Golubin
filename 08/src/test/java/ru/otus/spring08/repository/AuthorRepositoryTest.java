package ru.otus.spring08.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring08.domain.Author;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с авторами ")
@SpringBootTest
class AuthorRepositoryTest {

    private static final String DEFAULT_AUTHOR_NAME = "Лев";
    private static final String DEFAULT_AUTHOR_SURNAME = "Толстой";
    private static final String DEFAULT_AUTHOR_ID = "1";


    private static final String NEW_AUTHOR_NAME = "Аркадий";
    private static final String NEW_AUTHOR_SURNAME = "Гайдар";

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    @DisplayName("должен корректно добавлять в базу автора")
    void insert(){
        Author author  = new Author(DEFAULT_AUTHOR_ID,  NEW_AUTHOR_NAME, NEW_AUTHOR_SURNAME);
        authorRepository.save(author);


        Optional<Author> authordb  = authorRepository.findById(DEFAULT_AUTHOR_ID);

        assertThat(authordb).isNotEmpty().get().hasFieldOrPropertyWithValue("iD", DEFAULT_AUTHOR_ID);
        assertThat(authordb).isNotEmpty().get().hasFieldOrPropertyWithValue("name", NEW_AUTHOR_NAME);
        assertThat(authordb).isNotEmpty().get().hasFieldOrPropertyWithValue("surName", NEW_AUTHOR_SURNAME);

    }

    @Test
    @DisplayName("должен корректно удалять из базы автора")
    void deleteByID(){
        Author author  = new Author(DEFAULT_AUTHOR_ID,  DEFAULT_AUTHOR_NAME, DEFAULT_AUTHOR_SURNAME);
        authorRepository.delete(author);

        Optional<Author> authorDB  = authorRepository.findById(DEFAULT_AUTHOR_ID);
        assertThat(authorDB.isPresent()).isFalse();
    }
}