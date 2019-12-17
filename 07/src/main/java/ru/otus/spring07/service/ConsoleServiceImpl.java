package ru.otus.spring07.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring07.config.ApplicationSettings;
import ru.otus.spring07.domain.Author;
import ru.otus.spring07.domain.Book;
import ru.otus.spring07.domain.Comments;
import ru.otus.spring07.domain.Genre;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final MessageSource messageSource;

    private final IOService ioService;

    @Autowired
    private ApplicationSettings settings;

    @Autowired
    public ConsoleServiceImpl(IOService ioService, MessageSource messageSource) {
        this.ioService = ioService;
        this.messageSource = messageSource;
    }

    public Optional<Author> authorInsert() {
        ioService.printOut(
              messageSource.getMessage(
                      "author.name",
                      null,
                      settings.locale
              ));
        String name = ioService.readString();

        ioService.printOut(
              messageSource.getMessage(
                      "author.surname",
                      null,
                      settings.locale
              ));
        String surName = ioService.readString();

        return Optional.of( new Author(0L, name, surName));
    }

    public Optional<Author> authorUpdate() {

        ioService.printOut(
                messageSource.getMessage(
                        "author.ID",
                        null,
                        settings.locale
                ));
        Long id = ioService.readLong();
        ioService.readString();

        ioService.printOut(
                messageSource.getMessage(
                        "author.name",
                        null,
                        settings.locale
                ));
        String name = ioService.readString();

        ioService.printOut(
                messageSource.getMessage(
                        "author.surname",
                        null,
                        settings.locale
                ));
        String surName = ioService.readString();

        return Optional.of( new Author(id, name, surName));
    }

    public Long  authorDelete() {

        ioService.printOut(
                messageSource.getMessage(
                        "author.ID",
                        null,
                        settings.locale
                ));
        return ioService.readLong();
    }

    public void authorFindAll(List<Author> list){
        for(int i = 0; i < list.size(); i++) {
            Author author = list.get(i);
            ioService.printOut(
                    messageSource.getMessage(
                            "author.ID2",
                            null,
                            settings.locale
                    ) + String.valueOf(author.getID()));
            ioService.printOut(
                    messageSource.getMessage(
                            "author.name2",
                            null,
                            settings.locale
                    ) + author.getName());
            ioService.printOut(
                    messageSource.getMessage(
                            "author.surname2",
                            null,
                            settings.locale
                    ) + author.getSurName());
        }
    }


    public void authorErrorInsert(String message) {
        ioService.printOut(
                messageSource.getMessage(
                        "author.errorInsert",
                        null,
                        settings.locale
                )+ message);
    }

    public void authorErrorUpdate() {
        ioService.printOut(
                messageSource.getMessage(
                        "author.errorUpdate",
                        null,
                        settings.locale
                ));
    }

    public void authorErrorDelete() {
        ioService.printOut(
                messageSource.getMessage(
                        "author.errorDelete",
                        null,
                        settings.locale
                ));
    }


    public Optional<Genre> genreInsert() {
        ioService.printOut(
                messageSource.getMessage(
                        "genre.name",
                        null,
                        settings.locale
                ));
        String name = ioService.readString();

        return Optional.of( new Genre(0L, name));
    }

    public Optional<Genre> genreUpdate() {

        ioService.printOut(
                messageSource.getMessage(
                        "genre.ID",
                        null,
                        settings.locale
                ));
        Long id = ioService.readLong();
        ioService.readString();

        ioService.printOut(
                messageSource.getMessage(
                        "genre.name",
                        null,
                        settings.locale
                ));
        String name = ioService.readString();

        return Optional.of( new Genre(id, name));

    }

    public Long genreDelete() {

        ioService.printOut(
                messageSource.getMessage(
                        "genre.ID",
                        null,
                        settings.locale
                ));
        return ioService.readLong();
    }

    public void genreFindAll(List<Genre> list){
        for(int i = 0; i < list.size(); i++) {
            Genre genre = list.get(i);
            ioService.printOut(
                    messageSource.getMessage(
                            "genre.ID2",
                            null,
                            settings.locale
                    ) + String.valueOf(genre.getID()));
            ioService.printOut(
                    messageSource.getMessage(
                            "genre.name2",
                            null,
                            settings.locale
                    ) + genre.getName());
        }
    }


    public void genreErrorInsert(String message) {
        ioService.printOut(
                messageSource.getMessage(
                        "genre.errorInsert",
                        null,
                        settings.locale
                )+ " " + message);
    }

    public void genreErrorUpdate() {
        ioService.printOut(
                messageSource.getMessage(
                        "genre.errorUpdate",
                        null,
                        settings.locale
                ));
    }

    public void genreErrorDelete() {
        ioService.printOut(
                messageSource.getMessage(
                        "genre.errorDelete",
                        null,
                        settings.locale
                ));
    }


    public String bookAuthorNameIn() {
        ioService.printOut(
                messageSource.getMessage(
                        "book.authorName",
                        null,
                        settings.locale
                ));

        return ioService.readString();
    }

    public String bookAuthorSurNameIn() {
        ioService.printOut(
                messageSource.getMessage(
                        "book.authorSurName",
                        null,
                        settings.locale
                ));

        return ioService.readString();
    }

    public String bookGenreIn() {
        ioService.printOut(
                messageSource.getMessage(
                        "book.genre",
                        null,
                        settings.locale
                ));

        return ioService.readString();
    }

    public String bookNameIn() {
        ioService.printOut(
                messageSource.getMessage(
                        "book.name",
                        null,
                        settings.locale
                ));

        return ioService.readString();
    }

    public void printError(String errorName) {
        ioService.printOut(
                messageSource.getMessage(
                        errorName,
                        null,
                        settings.locale
                ));
    }

    public Long bookBookID() {
        ioService.printOut(
                messageSource.getMessage(
                        "book.ID",
                        null,
                        settings.locale
                ));

        Long bookID = ioService.readLong();
        ioService.readString();
        return bookID;
    }

    public Book bookDelete() {

        ioService.printOut(
                messageSource.getMessage(
                        "book.ID",
                        null,
                        settings.locale
                ));
        Long id = ioService.readLong();

        return new Book(id, new Author(0L,"",""), new Genre(0L,""), "");
    }

    public void bookFindAll(List<Book> list){
        for(int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            bookPrint(book);
        }
    }

    public void bookPrint(Book book){
            ioService.printOut(
                    messageSource.getMessage(
                            "book.ID2",
                            null,
                            settings.locale
                    ) + String.valueOf(book.getID()));
            ioService.printOut(
                    messageSource.getMessage(
                            "book.author2",
                            null,
                            settings.locale
                    ) + book.getAuthor().getName() + " " + book.getAuthor().getSurName());
            ioService.printOut(
                    messageSource.getMessage(
                            "book.name2",
                            null,
                            settings.locale
                    ) + book.getName());
            ioService.printOut(
                    messageSource.getMessage(
                            "book.genre2",
                            null,
                            settings.locale
                    ) + book.getGenre().getName());
    }

    public void bookErrorInsert(String message) {
        ioService.printOut(
                messageSource.getMessage(
                        "book.errorInsert",
                        null,
                        settings.locale
                ) + " " + message);
    }

    public void bookErrorUpdate() {
        ioService.printOut(
                messageSource.getMessage(
                        "book.errorUpdate",
                        null,
                        settings.locale
                ));
    }

    public void bookErrorDelete() {
        ioService.printOut(
                messageSource.getMessage(
                        "book.errorDelete",
                        null,
                        settings.locale
                ));
    }

    public String commentsText() {
        ioService.printOut(
                messageSource.getMessage(
                        "comments.name",
                        null,
                        settings.locale
                ));

        return ioService.readString();
    }

    public Long commentsCommentsID() {
        ioService.printOut(
                messageSource.getMessage(
                        "comments.ID",
                        null,
                        settings.locale
                ));

        Long commentsID = ioService.readLong();
        ioService.readString();
        return commentsID;

    }

    public void commentsFindAll(List<Comments> list){
        for(int i = 0; i < list.size(); i++) {
            Comments comments = list.get(i);
            ioService.printOut(
                    messageSource.getMessage(
                            "comments.ID2",
                            null,
                            settings.locale
                    ) + String.valueOf(comments.getID()));

            ioService.printOut(
                    messageSource.getMessage(
                            "book.ID2",
                            null,
                            settings.locale
                    ) + String.valueOf(comments.getBook().getID()));

            ioService.printOut(
                    messageSource.getMessage(
                            "comments.name2",
                            null,
                            settings.locale
                    ) + comments.getTextComment());
        }
    }
}
