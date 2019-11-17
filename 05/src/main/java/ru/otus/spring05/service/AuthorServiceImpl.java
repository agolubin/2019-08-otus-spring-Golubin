package ru.otus.spring05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring05.dao.AuthorDao;
import ru.otus.spring05.domain.Author;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao dao;
    private ConsoleService console;

    @Autowired
    public AuthorServiceImpl(AuthorDao dao, ConsoleService console) {
        this.dao     = dao;
        this.console = console;
    }

    public void insert() {
        Author author = console.authorInsert();
        if (author != null){
            try {
                dao.insert(author);
            } catch (SQLException e) {
                console.authorErrorInsert();
            }
        }
        else{
            console.authorErrorInsert();
        }
    }

    public void update() {
        Author author = console.authorUpdate();
        if (author != null){
            dao.update(author);
        }
        else{
            console.authorErrorUpdate();
        }
    }
    public void delete() {
        int authorID = console.authorDelete();
        if (authorID > 0){
            dao.deleteByID(authorID);
        }
        else{
            console.authorErrorDelete();
        }
    }

    public void findAll() {
        List<Author> list = new ArrayList<>();
        list = dao.findAll();
         console.authorFindAll(list);
    }
}
