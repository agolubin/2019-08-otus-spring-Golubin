package ru.otus.spring06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring06.exceptions.GenreExistException;
import ru.otus.spring06.repository.GenreRepository;
import ru.otus.spring06.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Genre> genre = consoleService.genreInsert();
        if (genre.isPresent()){
            try {
                genreRepository.insert(genre.get());
            } catch (GenreExistException e) {
                consoleService.genreErrorInsert(e.getMessage());
            }
        }
        else{
            consoleService.genreErrorInsert("");
        }
    }

    public void update() {
        Optional<Genre> genre = consoleService.genreUpdate();
        if (genre.isPresent()){
            try {
                genreRepository.update(genre.get());
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
        Optional<Genre> genre = genreRepository.getByID(consoleService.genreDelete());
        if (genre.get().getID() > 0){
            try {
                genreRepository.delete(genre.get());
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
