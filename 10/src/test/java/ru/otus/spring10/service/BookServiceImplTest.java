package ru.otus.spring10.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

import ru.otus.spring10.repository.AuthorRepository;
import ru.otus.spring10.repository.BookRepository;
import ru.otus.spring10.repository.GenreRepository;
import ru.otus.spring10.domain.Author;
import ru.otus.spring10.domain.Book;
import ru.otus.spring10.domain.Genre;
import ru.otus.spring10.Spring10Application;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Сервис для работы с книгами ")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Spring10Application.class)
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
    void insert(){

        Mockito.when(console.bookAuthorNameIn()).thenReturn(DEFAULT_AUTHOR_NAME);

        Mockito.when(console.bookAuthorSurNameIn()).thenReturn(DEFAULT_AUTHOR_SURNAME);
        Optional<Author> author = Optional.of(new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_NAME, DEFAULT_AUTHOR_SURNAME));
        Mockito.when(authorRepository.findAuthorByNameAndSurName(anyString(), anyString())).thenReturn(author);
        Optional<Genre> genre = Optional.of(new Genre(DEFAULT_GENRE_ID, DEFAULT_GENRE_NAME));
        Mockito.when(genreRepository.findGenreByName(any())).thenReturn(genre);

        Mockito.when(console.bookNameIn()).thenReturn(DEAULT_BOOK_NAME);

        Book book = new Book(DEFAULT_BOOK_ID, author.get(), genre.get(), DEAULT_BOOK_NAME);

        bookService.insert();

        ArgumentCaptor<Book> argument = ArgumentCaptor.forClass(Book.class);

        verify(bookRepository, Mockito.times(1)).save(argument.capture());
        assertEquals(DEAULT_BOOK_NAME, argument.getValue().getName());
        assertEquals(DEFAULT_AUTHOR_ID, argument.getValue().getAuthor().getID());
        assertEquals(DEFAULT_GENRE_ID, argument.getValue().getGenre().getID());


    }

}