package ru.otus.spring06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring06.exceptions.AuthorExistException;
import ru.otus.spring06.repository.AuthorRepository;
import ru.otus.spring06.domain.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final ConsoleService consoleService;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(ConsoleService consoleService, AuthorRepository authorRepository) {
        this.consoleService = consoleService;
        this.authorRepository = authorRepository;
    }

    public void insert() {
        Optional<Author> author =  consoleService.authorInsert();
        if (author.isPresent()){
            try {
                authorRepository.insert(author.get());
            } catch (AuthorExistException e) {
                consoleService.authorErrorInsert(e.getMessage());
            }
        }
        else{
            consoleService.authorErrorInsert("");
        }
    }

    public void update() {
        Optional<Author> author = consoleService.authorUpdate();
        if (author.isPresent()){
            try {
                authorRepository.update(author.get());
            }
            catch (Exception e){
                consoleService.authorErrorUpdate();
            }
        }
        else{
            consoleService.authorErrorUpdate();
        }
    }
    public void delete() {
        Optional<Author> author = authorRepository.getByID(consoleService.authorDelete());

        if (author.get().getID() > 0){
            try {
                authorRepository.delete(author.get());
            }
            catch (Exception e){
                consoleService.authorErrorDelete();
            }
        }
        else{
            consoleService.authorErrorDelete();
        }
    }

    public void findAll() {
        List<Author> list = new ArrayList<>();
        list = authorRepository.findAll();
         consoleService.authorFindAll(list);
    }
}
