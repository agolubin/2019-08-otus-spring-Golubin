package ru.otus.spring08.service;

import ru.otus.spring08.domain.Author;
import ru.otus.spring08.domain.Comments;
import ru.otus.spring08.domain.Genre;
import ru.otus.spring08.domain.Book;

import java.util.List;
import java.util.Optional;

public interface ConsoleService {

    void authorErrorInsert(String message);
    void authorErrorUpdate();
    void authorErrorDelete();
    void authorFindAll(List<Author> list);
    Optional<Author> authorInsert();
    Optional<Author> authorUpdate();
    String authorDelete();

    void genreErrorInsert(String message);
    void genreErrorUpdate();
    void genreErrorDelete();
    void genreFindAll(List<Genre> list);
    Optional<Genre> genreInsert();
    Optional<Genre> genreUpdate();
    String genreDelete();

    void bookErrorInsert(String message);
    void bookErrorUpdate();
    void bookErrorDelete();
    void bookPrint(Book book);
    void bookFindAll(List<Book> list);
    Book bookDelete();

    String bookAuthorNameIn();
    String bookAuthorSurNameIn();
    String bookGenreIn();
    String bookNameIn();
    String bookBookID();

    void printError(String errorName);

    String commentsText();
    String commentsCommentsID();
    void commentsFindAll(List<Comments> list);
}
