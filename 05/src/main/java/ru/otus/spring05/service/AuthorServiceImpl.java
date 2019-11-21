package ru.otus.spring05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring05.Exceptions.AuthorExistException;
import ru.otus.spring05.dao.AuthorDao;
import ru.otus.spring05.domain.Author;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;
    private final ConsoleService consoleService;

    @Autowired
    public AuthorServiceImpl(AuthorDao dao, ConsoleService consoleService) {
        this.dao     = dao;
        this.consoleService = consoleService;
    }

    public void insert() {
        Author author = consoleService.authorInsert();
        if (author != null){
            try {
                dao.insert(author);
            } catch (AuthorExistException e) {
                consoleService.authorErrorInsert(e.getMessage());
            }
        }
        else{
            consoleService.authorErrorInsert("");
        }
    }

    public void update() {
        Author author = consoleService.authorUpdate();
        if (author != null){
            dao.update(author);
        }
        else{
            consoleService.authorErrorUpdate();
        }
    }
    public void delete() {
        Long authorID = consoleService.authorDelete();
        if (authorID > 0){
            dao.deleteByID(authorID);
        }
        else{
            consoleService.authorErrorDelete();
        }
    }

    public void findAll() {
        List<Author> list = new ArrayList<>();
        list = dao.findAll();
         consoleService.authorFindAll(list);
    }
}
