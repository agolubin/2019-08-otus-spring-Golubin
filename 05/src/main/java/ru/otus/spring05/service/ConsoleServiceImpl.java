package ru.otus.spring05.service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring05.config.ApplicationSettings;
import ru.otus.spring05.domain.Author;
import ru.otus.spring05.domain.Book;
import ru.otus.spring05.domain.Genre;

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

    public Author authorInsert() {
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

        return new Author(0, name, surName);
    }

    public Author authorUpdate() {

        ioService.printOut(
                messageSource.getMessage(
                        "author.ID",
                        null,
                        settings.locale
                ));
        int id = ioService.readInt();
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

        return new Author(id, name, surName);
    }

    public int authorDelete() {

        ioService.printOut(
                messageSource.getMessage(
                        "author.ID",
                        null,
                        settings.locale
                ));
        int id = ioService.readInt();

        return id;
    }

    public void authorFindAll(List<Author> list){
        for(int i = 0; i < list.size(); i++) {
            Author author = list.get(i);
            ioService.printOut(
                    messageSource.getMessage(
                            "author.ID2",
                            null,
                            settings.locale
                    ) + String.valueOf(author.getAuthorID()));
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


    public void authorErrorInsert() {
        ioService.printOut(
                messageSource.getMessage(
                        "author.errorInsert",
                        null,
                        settings.locale
                ));
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


    public Genre genreInsert() {
        ioService.printOut(
                messageSource.getMessage(
                        "genre.name",
                        null,
                        settings.locale
                ));
        String name = ioService.readString();

        return new Genre(0, name);
    }

    public Genre genreUpdate() {

        ioService.printOut(
                messageSource.getMessage(
                        "genre.ID",
                        null,
                        settings.locale
                ));
        int id = ioService.readInt();
        ioService.readString();

        ioService.printOut(
                messageSource.getMessage(
                        "genre.name",
                        null,
                        settings.locale
                ));
        String name = ioService.readString();

        return new Genre(id, name);
    }

    public int genreDelete() {

        ioService.printOut(
                messageSource.getMessage(
                        "genre.ID",
                        null,
                        settings.locale
                ));
        int id = ioService.readInt();

        return id;
    }

    public void genreFindAll(List<Genre> list){
        for(int i = 0; i < list.size(); i++) {
            Genre genre = list.get(i);
            ioService.printOut(
                    messageSource.getMessage(
                            "genre.ID2",
                            null,
                            settings.locale
                    ) + String.valueOf(genre.getGenreID()));
            ioService.printOut(
                    messageSource.getMessage(
                            "genre.name2",
                            null,
                            settings.locale
                    ) + genre.getName());
        }
    }


    public void genreErrorInsert() {
        ioService.printOut(
                messageSource.getMessage(
                        "genre.errorInsert",
                        null,
                        settings.locale
                ));
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


    
    public Book bookInsert() {
        ioService.printOut(
                messageSource.getMessage(
                        "book.name",
                        null,
                        settings.locale
                ));
        String name = ioService.readString();

        ioService.printOut(
                messageSource.getMessage(
                        "book.authorName",
                        null,
                        settings.locale
                ));
        String authorName = ioService.readString();

        ioService.printOut(
                messageSource.getMessage(
                        "book.authorSurName",
                        null,
                        settings.locale
                ));
        String authorSurName = ioService.readString();

        ioService.printOut(
                messageSource.getMessage(
                        "book.genreName",
                        null,
                        settings.locale
                ));
        String genreName = ioService.readString();

        return new Book(0, name, authorName, authorSurName, genreName);
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

    public int bookBookID() {
        ioService.printOut(
                messageSource.getMessage(
                        "book.authorName",
                        null,
                        settings.locale
                ));

        return ioService.readInt();
    }

    public Book bookUpdate() {

        ioService.printOut(
                messageSource.getMessage(
                        "book.ID",
                        null,
                        settings.locale
                ));
        int id = ioService.readInt();
        ioService.readString();

        ioService.printOut(
                messageSource.getMessage(
                        "book.name",
                        null,
                        settings.locale
                ));
        String name = ioService.readString();

        ioService.printOut(
                messageSource.getMessage(
                        "book.authorName",
                        null,
                        settings.locale
                ));
        String authorName = ioService.readString();

        ioService.printOut(
                messageSource.getMessage(
                        "book.authorSurName",
                        null,
                        settings.locale
                ));
        String authorSurName = ioService.readString();

        ioService.printOut(
                messageSource.getMessage(
                        "book.genreName",
                        null,
                        settings.locale
                ));
        String genreName = ioService.readString();

        return new Book(id, name, authorName, authorSurName, genreName);
    }

    public int bookDelete() {

        ioService.printOut(
                messageSource.getMessage(
                        "book.ID",
                        null,
                        settings.locale
                ));
        int id = ioService.readInt();

        return id;
    }

    public void bookFindAll(List<Book> list){
        for(int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            ioService.printOut(
                    messageSource.getMessage(
                            "book.ID2",
                            null,
                            settings.locale
                    ) + String.valueOf(book.getBookID()));
            ioService.printOut(
                    messageSource.getMessage(
                            "book.author2",
                            null,
                            settings.locale
                    ) + book.getAuthorName() + " " + book.getAuthorSurName());
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
                    ) + book.getGenreName());
        }
    }


    public void bookErrorInsert() {
        ioService.printOut(
                messageSource.getMessage(
                        "book.errorInsert",
                        null,
                        settings.locale
                ));
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

}
