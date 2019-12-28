package ru.otus.spring08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring08.exceptions.BookExistException;
import ru.otus.spring08.repository.AuthorRepository;
import ru.otus.spring08.repository.BookRepository;
import ru.otus.spring08.repository.GenreRepository;
import ru.otus.spring08.domain.Author;
import ru.otus.spring08.domain.Book;
import ru.otus.spring08.domain.Genre;

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

        Optional<Author> author = authorRepository.findAuthorByNameAndSurName(authorName, authorSurName);

        if (!author.isPresent()){
            consoleService.printError("book.errorAuthorExist");
            return;
        }

        String genreName    = consoleService.bookGenreIn();

        Optional<Genre>  genre = genreRepository.findGenreByName(genreName);

        if (!genre.isPresent()){
            consoleService.printError("book.errorGenreExist");
            return;
        }

        String bookName = consoleService.bookNameIn();

        Book book = new Book("", author.get(), genre.get(), bookName);
            bookRepository.save(book);
    }

    public void update() {
        String bookid = consoleService.bookBookID();
        Optional<Book> book2 = bookRepository.findById(bookid);

        if (!book2.isPresent()){
            consoleService.printError("book.errorBookExist");
            return;
        }

        String authorName    = consoleService.bookAuthorNameIn();
        String authorSurName = consoleService.bookAuthorSurNameIn();

        Optional<Author> author = authorRepository.findAuthorByNameAndSurName(authorName, authorSurName);

        if (!author.isPresent()){
            consoleService.printError("book.errorAuthorExist");
            return;
        }

        String genreName    = consoleService.bookGenreIn();

        Optional<Genre>  genre = genreRepository.findGenreByName(genreName);

        if (!genre.isPresent()){
            consoleService.printError("book.errorGenreExist");
            return;
        }

        String bookName = consoleService.bookNameIn();

        Book book = new Book("", author.get(), genre.get(), bookName);
        bookRepository.save(book);
    }


    public void delete() {
        Book book = consoleService.bookDelete();

        if (book.getID() != ""){
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
