package ru.otus.spring10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring10.repository.AuthorRepository;
import ru.otus.spring10.repository.BookRepository;
import ru.otus.spring10.repository.GenreRepository;
import ru.otus.spring10.domain.Author;
import ru.otus.spring10.domain.Book;
import ru.otus.spring10.domain.Genre;

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

    @Override
    public Book insert(String authorName, String authorSurName, String genreName, String bookName) {
        Optional<Author> author = authorRepository.findAuthorByNameAndSurName(authorName, authorSurName);
        Optional<Genre> genre = genreRepository.findGenreByName(genreName);
        if ((author.isPresent())&&(genre.isPresent())) {
            Book book = new Book(0L, author.get(), genre.get(), bookName);
            bookRepository.save(book);
            return book;
        }
        else{
            return new Book();
        }


    }

    @Override
    public void update(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
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

        Book book = new Book(0L, author.get(), genre.get(), bookName);
            bookRepository.save(book);
    }

    public void update() {
        Long bookid = consoleService.bookBookID();
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

        Book book = new Book(0L, author.get(), genre.get(), bookName);
        bookRepository.save(book);
    }


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

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        Book book = bookRepository.findById(id).get();
        return book;
    }
}
