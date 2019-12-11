package ru.otus.spring06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring06.Exceptions.GenreExistException;
import ru.otus.spring06.Repository.GenreRepository;
import ru.otus.spring06.domain.Genre;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final ConsoleService consoleService;
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, ConsoleService consoleService) {
        this.genreRepository = genreRepository;
        this.consoleService  = consoleService;
    }

    public void insert() {
        Genre genre = consoleService.genreInsert();
        if (genre != null){
            try {
                genreRepository.insert(genre);
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
            try {
                genreRepository.update(genre);
            }
            catch (Exception e){
                consoleService.genreErrorUpdate();
            }
        }
        else{
            consoleService.genreErrorUpdate();
        }
    }
    public void delete() {
        Genre genre = genreRepository.getByID(consoleService.genreDelete());
        if (genre.getID() > 0){
            try {
                genreRepository.delete(genre);
            }
            catch (Exception e){
                consoleService.genreErrorDelete();
            }
        }
        else{
            consoleService.genreErrorDelete();
        }
    }

    public void findAll() {
        List<Genre> list = new ArrayList<>();
        list = genreRepository.findAll();
         consoleService.genreFindAll(list);
    }
}
