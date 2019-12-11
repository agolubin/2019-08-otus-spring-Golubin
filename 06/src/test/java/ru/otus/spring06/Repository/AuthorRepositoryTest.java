package ru.otus.spring06.Repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring06.Exceptions.AuthorExistException;
import ru.otus.spring06.Repository.AuthorRepository;
import ru.otus.spring06.Repository.AuthorRepositoryJpa;
import ru.otus.spring06.domain.Author;

import ru.otus.spring06.spring06Application;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с авторами ")
@Import({AuthorRepositoryJpa.class})
@ContextConfiguration(classes=spring06Application.class)
@DataJpaTest
class AuthorRepositoryTest {

    private static final String DEFAULT_AUTHOR_NAME = "Лев";
    private static final String DEFAULT_AUTHOR_SURNAME = "Толстой";
    private static final Long DEFAULT_AUTHOR_ID = 1L;


    private static final int BOOK_COUNT_NEW_BOOK = 1;
    private static final int BOOK_COUNT_BY_ID = 1;
    private static final Long DEFAULT_BOOK_ID = 1L;
    private static final Long DEFAULT_GENRE_ID = 2L;
    private static final int BOOK_COUNT_DELETE_BOOK = 0;

    private static final String NEW_AUTHOR_NAME = "Аркадий";
    private static final String NEW_AUTHOR_SURNAME = "Гайдар";
    private static final Long NEW_AUTHOR_ID = 2L;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("должен корректно добавлять в базу автора")
    void insert() throws AuthorExistException {
        Author author  = new Author(0L,  NEW_AUTHOR_NAME, NEW_AUTHOR_SURNAME);
        authorRepository.insert(author);

        Author authordb  = authorRepository.getByID(NEW_AUTHOR_ID);

        assertThat(authordb).hasFieldOrPropertyWithValue("ID", NEW_AUTHOR_ID);
        assertThat(authordb).hasFieldOrPropertyWithValue("name", NEW_AUTHOR_NAME);
        assertThat(authordb).hasFieldOrPropertyWithValue("surName", NEW_AUTHOR_SURNAME);

    }

    @Test
    @DisplayName("должен корректно удалять из базы автора")
    void deleteByID() throws AuthorExistException{
        Author author  = new Author(DEFAULT_AUTHOR_ID,  DEFAULT_AUTHOR_NAME, DEFAULT_AUTHOR_SURNAME);
        authorRepository.delete(author);
        Author authorDB  = authorRepository.getByID(DEFAULT_AUTHOR_ID);
        assertThat(authorDB).isNull();
    }

    @Test
    @DisplayName("должен корректно искать автора в базе по ID")
    void getAuthorByID() {
        Author author  = new Author(DEFAULT_AUTHOR_ID,  DEFAULT_AUTHOR_NAME, DEFAULT_AUTHOR_SURNAME);
        Author authordb = authorRepository.getByID(DEFAULT_AUTHOR_ID);
        assertThat(authordb).hasFieldOrPropertyWithValue("ID", DEFAULT_AUTHOR_ID);
        assertThat(authordb).hasFieldOrPropertyWithValue("name", DEFAULT_AUTHOR_NAME);
        assertThat(authordb).hasFieldOrPropertyWithValue("surName", DEFAULT_AUTHOR_SURNAME);
    }
}