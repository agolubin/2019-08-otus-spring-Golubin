package ru.otus.spring06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring06.exceptions.BookExistException;
import ru.otus.spring06.repository.AuthorRepository;
import ru.otus.spring06.repository.BookRepository;
import ru.otus.spring06.repository.GenreRepository;
import ru.otus.spring06.domain.Author;
import ru.otus.spring06.domain.Book;
import ru.otus.spring06.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final ConsoleService consoleService;
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, ConsoleService consoleService) {
        this.bookRepository   = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository  = genreRepository;
        this.consoleService   = consoleService;
    }

    public void insert() {
        String authorName    = consoleService.bookAuthorNameIn();
        String authorSurName = consoleService.bookAuthorSurNameIn();

        Author author = authorRepository.getAuthorByName(authorName, authorSurName);

        if (author == null){
            consoleService.printError("book.errorAuthorExist");
            return;
        }

        String genreName    = consoleService.bookGenreIn();

        Genre genre = genreRepository.getGenreByName(genreName);

        if (genre == null){
            consoleService.printError("book.errorGenreExist");
            return;
        }

        String bookName = consoleService.bookNameIn();

        Book book = new Book(0L, author, genre, bookName);
        try {
            bookRepository.insert(book);
        } catch (BookExistException e) {
             consoleService.bookErrorInsert(e.getMessage());
        }
    }

    public void update() {
        Long bookid = consoleService.bookBookID();
        Optional<Book> book2 = bookRepository.getByID(bookid);

        if (book2.isPresent()){
            consoleService.printError("book.errorBookExist");
            return;
        }

        String authorName    = consoleService.bookAuthorNameIn();
        String authorSurName = consoleService.bookAuthorSurNameIn();

        Author author = authorRepository.getAuthorByName(authorName, authorSurName);

        if (author == null){
            consoleService.printError("book.errorAuthorExist");
            return;
        }

        String genreName    = consoleService.bookGenreIn();

        Genre genre = genreRepository.getGenreByName(genreName);

        if (genre == null){
            consoleService.printError("book.errorGenreExist");
            return;
        }

        String bookName = consoleService.bookNameIn();

        Book book = new Book(0L, author, genre, bookName);
        try {
            bookRepository.insert(book);
        } catch (BookExistException e) {
            consoleService.bookErrorInsert(e.getMessage());
        }    }


    public void delete() {
        Book book = consoleService.bookDelete();

        if (book.getID() > 0){
            try {
                bookRepository.delete(book);
            }
            catch (Exception e){
                consoleService.bookErrorUpdate();
            }
        }
        else{
            consoleService.bookErrorDelete();
        }    }

    public void findAll() {
        List<Book> list = bookRepository.findAll();
         consoleService.bookFindAll(list);
    }
}
