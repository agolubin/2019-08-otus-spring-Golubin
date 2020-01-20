package ru.otus.spring10.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring10.domain.Book;

@Controller
public class PageController {

    @GetMapping("/add_book")
    public String showAddForm(Book book) {
        return "add_book";
    }

}
