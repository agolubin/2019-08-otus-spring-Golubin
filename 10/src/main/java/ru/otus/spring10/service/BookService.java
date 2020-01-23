package ru.otus.spring10.service;

import ru.otus.spring10.domain.Book;

import java.util.List;

public interface BookService {

    void insert();
    void update();
    void delete();

    Book insert(String authorName, String authorSurName, String genreName, String bookName);
    void update(Book book);
    void delete(Long id);
    Book findById(Long id);
    List<Book> findAll();
}
