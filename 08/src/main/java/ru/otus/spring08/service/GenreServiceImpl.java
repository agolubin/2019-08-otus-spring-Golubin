package ru.otus.spring08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring08.exceptions.GenreExistException;
import ru.otus.spring08.repository.GenreRepository;
import ru.otus.spring08.domain.Genre;

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
                genreRepository.save(genre.get());
        }
        else{
            consoleService.genreErrorInsert("");
        }
    }

    public void update() {
        Optional<Genre> genre = consoleService.genreUpdate();
        if (genre.isPresent()){
                genreRepository.save(genre.get());
        }
        else{
            consoleService.genreErrorUpdate();
        }
    }
    public void delete() {
        Optional<Genre> genre = genreRepository.findById(consoleService.genreDelete());
        if (genre.get().getID() != ""){
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
