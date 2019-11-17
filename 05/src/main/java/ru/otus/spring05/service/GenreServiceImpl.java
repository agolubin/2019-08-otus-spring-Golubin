package ru.otus.spring05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring05.dao.GenreDao;
import ru.otus.spring05.domain.Genre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreDao dao;
    private ConsoleService console;

    @Autowired
    public GenreServiceImpl(GenreDao dao, ConsoleService console) {
        this.dao     = dao;
        this.console = console;
    }

    public void insert() {
        Genre genre = console.genreInsert();
        if (genre != null){
            try {
                dao.insert(genre);
            } catch (SQLException e) {
                console.genreErrorInsert();
            }
        }
        else{
            console.genreErrorInsert();
        }
    }

    public void update() {
        Genre genre = console.genreUpdate();
        if (genre != null){
            dao.update(genre);
        }
        else{
            console.genreErrorUpdate();
        }
    }
    public void delete() {
        int genreID = console.genreDelete();
        if (genreID > 0){
            dao.deleteByID(genreID);
        }
        else{
            console.genreErrorDelete();
        }
    }

    public void findAll() {
        List<Genre> list = new ArrayList<>();
        list = dao.findAll();
         console.genreFindAll(list);
    }
}
