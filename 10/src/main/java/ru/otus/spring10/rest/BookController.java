package ru.otus.spring10.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring10.domain.Book;
import ru.otus.spring10.service.AuthorService;
import ru.otus.spring10.service.BookService;
import ru.otus.spring10.service.GenreService;

import java.util.List;


@RestController
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;


    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/Add_Book")
    public String showAddForm(Book book) {
        return "add_book";
    }

    @GetMapping("/api/books")
    public List<Book> listPage() {
        List<Book> listBook = bookService.findAll();
        return listBook;
    }

    @PostMapping("/books/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.insert(book.getAuthor().getName(), book.getAuthor().getSurName(), book.getGenre().getName(), book.getName());
    }

    @GetMapping("/books/update/{id}")
    public Book editPage(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return book;
    }

    @PostMapping("/books/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.update(book);
        return book;
    }

    @PostMapping("/books/delete/{id}")
    public void deleteBook(@PathVariable("id") long id, Model model) {
        bookService.delete(id);
    }
}
