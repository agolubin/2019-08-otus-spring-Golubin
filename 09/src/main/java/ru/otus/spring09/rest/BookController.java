package ru.otus.spring09.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring09.domain.Book;
import ru.otus.spring09.service.AuthorService;
import ru.otus.spring09.service.BookService;
import ru.otus.spring09.service.GenreService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;


    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService){
        this.bookService   = bookService;
        this.authorService = authorService;
        this.genreService  = genreService;
    }

    @GetMapping("/Add_Book")
    public String showAddForm(Book book) {
        return "add_book";
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @PostMapping("/books/add")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        bookService.insert(book.getAuthor().getName(), book.getAuthor().getSurName(), book.getGenre().getName(), book.getName());
        return "redirect:/";
    }
    @GetMapping("/books/update/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "update_Book";
    }

    @PostMapping("/books/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        bookService.update(book);
        return "redirect:/";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        bookService.delete(id);
        return "redirect:/";
    }

}
