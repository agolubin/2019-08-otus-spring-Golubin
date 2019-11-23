package ru.otus.spring05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring05.Exceptions.BookExistException;
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

    private final BookDao dao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final ConsoleService consoleService;

    @Autowired
    public BookServiceImpl(BookDao dao, AuthorDao authorDao, GenreDao genreDao, ConsoleService consoleService) {
        this.dao       = dao;
        this.authorDao = authorDao;
        this.genreDao  = genreDao;
        this.consoleService = consoleService;
    }

    public void insert() {
        String authorName    = consoleService.bookAuthorNameIn();
        String authorSurName = consoleService.bookAuthorSurNameIn();

        Author author = authorDao.getAuthorByName(authorName, authorSurName);

        if (author == null){
            consoleService.printError("book.errorAuthorExist");
            return;
        }

        String genreName    = consoleService.bookGenreIn();

        Genre genre = genreDao.getGenreByName(genreName);

        if (genre == null){
            consoleService.printError("book.errorGenreExist");
            return;
        }

        String bookName = consoleService.bookNameIn();

        Book book = new Book(0L, author, genre, bookName);
        try {
             dao.insert(book);
        } catch (BookExistException e) {
             consoleService.bookErrorInsert(e.getMessage());
        }
    }

    public void update() {
        Long bookid = consoleService.bookBookID();
        int bookExist = dao.countByID(bookid);

        if (bookExist == 0){
            consoleService.printError("book.errorBookExist");
            return;
        }

        String authorName    = consoleService.bookAuthorNameIn();
        String authorSurName = consoleService.bookAuthorSurNameIn();

        Author author = authorDao.getAuthorByName(authorName, authorSurName);

        if (author == null){
            consoleService.printError("book.errorAuthorExist");
            return;
        }

        String genreName    = consoleService.bookGenreIn();

        Genre genre = genreDao.getGenreByName(genreName);

        if (genre == null){
            consoleService.printError("book.errorGenreExist");
            return;
        }

        String bookName = consoleService.bookNameIn();

        Book book = new Book(0L, author, genre, bookName);
        try {
            dao.insert(book);
        } catch (BookExistException e) {
            consoleService.bookErrorInsert(e.getMessage());
        }    }


    public void delete() {
        Long bookid = consoleService.bookBookID();
        int bookExist = dao.countByID(bookid);

        if (bookExist == 0){
             consoleService.printError("book.errorBookExist");
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
         consoleService.bookFindAll(list);
    }
}
