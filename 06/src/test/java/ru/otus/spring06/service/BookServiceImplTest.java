package ru.otus.spring06.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

import ru.otus.spring06.Exceptions.BookExistException;
import ru.otus.spring06.Repository.AuthorRepository;
import ru.otus.spring06.Repository.BookRepository;
import ru.otus.spring06.Repository.GenreRepository;
import ru.otus.spring06.domain.Author;
import ru.otus.spring06.domain.Book;
import ru.otus.spring06.domain.Genre;
import ru.otus.spring06.spring06Application;

import java.sql.SQLException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Сервис для работы с книгами ")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= spring06Application.class)
class BookServiceImplTest {

    private static final Long DEFAULT_GENRE_ID = 1L;
    private static final Long DEFAULT_BOOK_ID = 0L;
    private static final String DEFAULT_GENRE_NAME = "Роман";
    private static final Long DEFAULT_AUTHOR_ID = 1L;
    private static final String DEFAULT_AUTHOR_NAME    = "Лев";
    private static final String DEFAULT_AUTHOR_SURNAME = "Толстой";
    private static final String DEAULT_BOOK_NAME = "Война и мир";

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private ConsoleServiceImpl console;

    @Autowired
    private BookServiceImpl bookService;


    @Test
    @DisplayName(" долженн корректно добавлять книгу")
    void insert() throws BookExistException {

        Mockito.when(console.bookAuthorNameIn()).thenReturn(DEFAULT_AUTHOR_NAME);

        Mockito.when(console.bookAuthorSurNameIn()).thenReturn(DEFAULT_AUTHOR_SURNAME);
        Author author = new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_NAME, DEFAULT_AUTHOR_SURNAME);
        Mockito.when(authorRepository.getAuthorByName(anyString(), anyString())).thenReturn(author);
        Genre genre = new Genre(DEFAULT_GENRE_ID, DEFAULT_GENRE_NAME);
        Mockito.when(genreRepository.getGenreByName(any())).thenReturn(genre);

        Mockito.when(console.bookNameIn()).thenReturn(DEAULT_BOOK_NAME);

        Book book = new Book(DEFAULT_BOOK_ID, author, genre, DEAULT_BOOK_NAME);

        bookService.insert();

        ArgumentCaptor<Book> argument = ArgumentCaptor.forClass(Book.class);

        verify(bookRepository, Mockito.times(1)).insert(argument.capture());
        assertEquals(DEAULT_BOOK_NAME, argument.getValue().getName());
        assertEquals(DEFAULT_AUTHOR_ID, argument.getValue().getAuthor().getID());
        assertEquals(DEFAULT_GENRE_ID, argument.getValue().getGenre().getID());


    }

}