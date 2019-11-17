package ru.otus.spring05.service;

import ru.otus.spring05.domain.Author;
import ru.otus.spring05.domain.Genre;
import ru.otus.spring05.domain.Book;

import java.util.List;

public interface ConsoleService {

    void authorErrorInsert();
    void authorErrorUpdate();
    void authorErrorDelete();
    void authorFindAll(List<Author> list);
    Author authorInsert();
    Author authorUpdate();
    int authorDelete();

    void genreErrorInsert();
    void genreErrorUpdate();
    void genreErrorDelete();
    void genreFindAll(List<Genre> list);
    Genre genreInsert();
    Genre genreUpdate();
    int genreDelete();

    void bookErrorInsert();
    void bookErrorUpdate();
    void bookErrorDelete();
    void bookFindAll(List<Book> list);
    Book bookInsert();
    Book bookUpdate();
    int bookDelete();

    String bookAuthorNameIn();
    String bookAuthorSurNameIn();
    String bookGenreIn();
    String bookNameIn();
    int bookBookID();

    void printError(String errorName);
}
