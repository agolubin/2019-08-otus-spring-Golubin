package ru.otus.spring05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring05.Exceptions.GenreExistException;
import ru.otus.spring05.dao.GenreDao;
import ru.otus.spring05.domain.Genre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;
    private final ConsoleService consoleService;

    @Autowired
    public GenreServiceImpl(GenreDao dao, ConsoleService consoleService) {
        this.dao     = dao;
        this.consoleService = consoleService;
    }

    public void insert() {
        Genre genre = consoleService.genreInsert();
        if (genre != null){
            try {
                dao.insert(genre);
            } catch (GenreExistException e) {
                consoleService.genreErrorInsert(e.getMessage());
            }
        }
        else{
            consoleService.genreErrorInsert("");
        }
    }

    public void update() {
        Genre genre = consoleService.genreUpdate();
        if (genre != null){
            dao.update(genre);
        }
        else{
            consoleService.genreErrorUpdate();
        }
    }
    public void delete() {
        Long genreID = consoleService.genreDelete();
        if (genreID > 0){
            dao.deleteByID(genreID);
        }
        else{
            consoleService.genreErrorDelete();
        }
    }

    public void findAll() {
        List<Genre> list = new ArrayList<>();
        list = dao.findAll();
         consoleService.genreFindAll(list);
    }
}
