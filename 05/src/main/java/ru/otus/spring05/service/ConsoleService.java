package ru.otus.spring05.service;

import ru.otus.spring05.domain.Author;
import ru.otus.spring05.domain.Genre;
import ru.otus.spring05.domain.Book;

import java.util.List;

public interface ConsoleService {

    void authorErrorInsert(String message);
    void authorErrorUpdate();
    void authorErrorDelete();
    void authorFindAll(List<Author> list);
    Author authorInsert();
    Author authorUpdate();
    Long authorDelete();

    void genreErrorInsert(String message);
    void genreErrorUpdate();
    void genreErrorDelete();
    void genreFindAll(List<Genre> list);
    Genre genreInsert();
    Genre genreUpdate();
    Long genreDelete();

    void bookErrorInsert(String message);
    void bookErrorUpdate();
    void bookErrorDelete();
    void bookFindAll(List<Book> list);
    Book bookInsert();
    Book bookUpdate();
    Long bookDelete();

    String bookAuthorNameIn();
    String bookAuthorSurNameIn();
    String bookGenreIn();
    String bookNameIn();
    Long bookBookID();

    void printError(String errorName);
}
