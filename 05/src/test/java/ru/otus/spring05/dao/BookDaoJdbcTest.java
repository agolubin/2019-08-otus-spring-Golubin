package ru.otus.spring05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring05.domain.Book;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DAO для работы с книгами ")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private static final int NEW_BOOK_ID = 2;
    private static final int BOOK_COUNT_NEW_BOOK = 1;
    private static final int BOOK_COUNT_BY_ID = 1;
    private static final int DEFAULT_BOOK_ID = 1;
    private static final int BOOK_COUNT_DELETE_BOOK = 0;
    private static final String DEFAULT_BOOK_NAME = "Война и мир";

    @Autowired
    private BookDaoJdbc bookDaoJdbc;
    @Test
    @DisplayName("должен корректно добавлять в базу книгу")
    void insert() throws SQLException {
        Book book  = new Book(0, 2, 2, DEFAULT_BOOK_NAME);
        try {
            Book book2 = bookDaoJdbc.insert(book);
        }
        catch (SQLException e)
        {

        }
        assertThat(bookDaoJdbc.checkByID(NEW_BOOK_ID)).isEqualTo(BOOK_COUNT_NEW_BOOK);

    }

    @Test
    @DisplayName("должен корректно удалять из базы книгу")
    void deleteByID() {
        bookDaoJdbc.deleteByID(DEFAULT_BOOK_ID);
        assertThat(bookDaoJdbc.checkByID(NEW_BOOK_ID)).isEqualTo(BOOK_COUNT_DELETE_BOOK);
    }

    @Test
    @DisplayName("должен корректно искать книгу в базе по названию, жанру и автору")
    void getBookByBook() {
        Book book  = new Book(0, 1, 1, DEFAULT_BOOK_NAME);
        Book book2 = bookDaoJdbc.getBookByBook(book);
        assertThat(book2).hasFieldOrPropertyWithValue("bookID", DEFAULT_BOOK_ID);
    }

    @Test
    @DisplayName("должен корректно проверять наличие в базе книги по ID книги")
    void checkByID() {
        assertThat(bookDaoJdbc.checkByID(DEFAULT_BOOK_ID)).isEqualTo(BOOK_COUNT_BY_ID);
    }
}