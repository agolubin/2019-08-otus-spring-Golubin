package ru.otus.spring05.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import ru.otus.spring05.dao.AuthorDao;
import ru.otus.spring05.dao.BookDao;
import ru.otus.spring05.dao.GenreDao;
import ru.otus.spring05.domain.Author;
import ru.otus.spring05.domain.Book;
import ru.otus.spring05.domain.Genre;

import java.sql.SQLException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Сервис для работы с книгами ")
@SpringBootTest
@ExtendWith(SpringExtension.class)
class BookServiceImplTest {

    private static final int DEFAULT_GENRE_NAME_ID = 1;
    private static final int DEFAULT_BOOK_ID = 0;
    private static final String DEFAULT_GENRE_NAME = "Роман";
    private static final int DEFAULT_AUTHOR_ID = 1;
    private static final String DEFAULT_AUTHOR_NAME    = "Лев";
    private static final String DEFAULT_AUTHOR_SURNAME = "Толстой";
    private static final String DEAULT_BOOK_NAME = "Война и мир";

    @MockBean
    private IOService ioMock;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private GenreDao genreDao;

    @MockBean
    private BookDao bookDao;

    @MockBean
    private ConsoleServiceImpl console;

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private MessageSource messageMock;

    @Test
    @DisplayName(" долженн корректно добавлять книгу")
    void insert() throws SQLException {

        Mockito.when(console.bookAuthorNameIn()).thenReturn(DEFAULT_AUTHOR_NAME);
        Mockito.when(console.bookAuthorSurNameIn()).thenReturn(DEFAULT_AUTHOR_SURNAME);
        Author author = new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_NAME, DEFAULT_AUTHOR_SURNAME);
        Mockito.when(authorDao.getAuthorByAuthor(any())).thenReturn(author);
        Genre genre = new Genre(DEFAULT_GENRE_NAME_ID, DEFAULT_GENRE_NAME);
        Mockito.when(genreDao.getGenreByGenre(any())).thenReturn(genre);

        Mockito.when(console.bookNameIn()).thenReturn(DEAULT_BOOK_NAME);

        Book book = new Book(DEFAULT_BOOK_ID,author.getAuthorID(), genre.getGenreID(), DEAULT_BOOK_NAME);

        bookService.insert();

        verify(bookDao, Mockito.times(1)).insert(book);

    }

}