package ru.otus.spring05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring05.dao.AuthorDao;
import ru.otus.spring05.dao.BookDao;
import ru.otus.spring05.dao.GenreDao;
import ru.otus.spring05.domain.Author;
import ru.otus.spring05.domain.Book;
import ru.otus.spring05.domain.Genre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDao dao;
    private AuthorDao authorDao;
    private GenreDao genreDao;
    private ConsoleService console;

    @Autowired
    public BookServiceImpl(BookDao dao, AuthorDao authorDao, GenreDao genreDao, ConsoleService console) {
        this.dao       = dao;
        this.authorDao = authorDao;
        this.genreDao  = genreDao;
        this.console   = console;
    }

    public void insert() {
        String authorName    = console.bookAuthorNameIn();
        String authorSurName = console.bookAuthorSurNameIn();

        Author authorIn = new Author(0, authorName, authorSurName);
        Author author = authorDao.getAuthorByAuthor(authorIn);

        if (author == null){
            console.printError("book.errorAuthorExist");
            return;
        }

        String genreName    = console.bookGenreIn();

        Genre genreIn = new Genre(0, genreName);
        Genre genre = genreDao.getGenreByGenre(genreIn);

        if (genre == null){
            console.printError("book.errorGenreExist");
            return;
        }

        String bookName = console.bookNameIn();

        Book book = new Book(0,author.getAuthorID(), genre.getGenreID(), bookName);
        try {
             dao.insert(book);
        } catch (SQLException e) {
             console.bookErrorInsert();
        }
    }

    public void update() {
        int bookid = console.bookBookID();
        int bookExist = dao.checkByID(bookid);

        if (bookExist == 0){
            console.printError("book.errorBookExist");
            return;
        }

        String authorName    = console.bookAuthorNameIn();
        String authorSurName = console.bookAuthorSurNameIn();

        Author authorIn = new Author(0, authorName, authorSurName);
        Author author = authorDao.getAuthorByAuthor(authorIn);

        if (author == null){
            console.printError("book.errorAuthorExist");
            return;
        }

        String genreName    = console.bookGenreIn();

        Genre genreIn = new Genre(0, genreName);
        Genre genre = genreDao.getGenreByGenre(genreIn);

        if (genre == null){
            console.printError("book.errorGenreExist");
            return;
        }

        String bookName = console.bookNameIn();

        Book book = new Book(0,author.getAuthorID(), genre.getGenreID(), bookName);
        try {
            dao.insert(book);
        } catch (SQLException e) {
            console.bookErrorInsert();
        }    }


    public void delete() {
        int bookid = console.bookBookID();
        int bookExist = dao.checkByID(bookid);

        if (bookExist == 0){
             console.printError("book.errorBookExist");
             return;
        }
        else
        {
            dao.deleteByID(bookid);
        };
    }

    public void findAll() {
        List<Book> list = new ArrayList<>();
        list = dao.findAll();
         console.bookFindAll(list);
    }
}
