package ru.otus.spring05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring05.Exceptions.BookExistException;
import ru.otus.spring05.domain.Author;
import ru.otus.spring05.domain.Book;
import ru.otus.spring05.domain.Genre;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DAO для работы с книгами ")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private static final Long NEW_BOOK_ID = 2L;
    private static final int BOOK_COUNT_NEW_BOOK = 1;
    private static final int BOOK_COUNT_BY_ID = 1;
    private static final Long DEFAULT_BOOK_ID = 1L;
    private static final Long DEFAULT_GENRE_ID = 1L;
    private static final Long DEFAULT_AUTHOR_ID = 1L;
    private static final int BOOK_COUNT_DELETE_BOOK = 0;
    private static final String DEFAULT_BOOK_NAME = "Война и мир";
    private static final String NEW_BOOK_NAME = "Анна Каренина";

    @Autowired
    private BookDaoJdbc bookDaoJdbc;
    @Test
    @DisplayName("должен корректно добавлять в базу книгу")
    void insert() throws BookExistException{
        Book book  = new Book(0L, new Author(DEFAULT_AUTHOR_ID, "", ""), new Genre(DEFAULT_GENRE_ID, ""), NEW_BOOK_NAME);
        Book book2 = bookDaoJdbc.insert(book);
        assertThat(bookDaoJdbc.countByID(NEW_BOOK_ID)).isEqualTo(BOOK_COUNT_NEW_BOOK);

        Book book3 = bookDaoJdbc.getBookByID(book2.getBookID());
        Author author = book3.getAuthor();
        Genre genre   = book3.getGenre();
        assertThat(book3).hasFieldOrPropertyWithValue("bookID", NEW_BOOK_ID);
        assertThat(genre).hasFieldOrPropertyWithValue("genreID", DEFAULT_GENRE_ID);
        assertThat(author).hasFieldOrPropertyWithValue("authorID", DEFAULT_AUTHOR_ID);

    }

    @Test
    @DisplayName("должен корректно удалять из базы книгу")
    void deleteByID() {
        bookDaoJdbc.deleteByID(DEFAULT_BOOK_ID);
        assertThat(bookDaoJdbc.countByID(DEFAULT_BOOK_ID)).isEqualTo(BOOK_COUNT_DELETE_BOOK);
    }

    @Test
    @DisplayName("должен корректно искать книгу в базе по названию, жанру и автору")
    void getBookByBook() {
        Book book  = new Book(0L, new Author(1L, "", ""), new Genre(1L, ""), DEFAULT_BOOK_NAME);
        Book book2 = bookDaoJdbc.getBookByParam(book.getAuthor().getAuthorID(), book.getGenre().getGenreID(), book.getName());
        assertThat(book2).hasFieldOrPropertyWithValue("bookID", DEFAULT_BOOK_ID);
    }

    @Test
    @DisplayName("должен корректно проверять наличие в базе книги по ID книги")
    void checkByID() {
        assertThat(bookDaoJdbc.countByID(DEFAULT_BOOK_ID)).isEqualTo(BOOK_COUNT_BY_ID);
    }
}