package ru.otus.spring08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring08.exceptions.AuthorExistException;
import ru.otus.spring08.repository.AuthorRepository;
import ru.otus.spring08.domain.Author;

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
                authorRepository.save(author.get());
        }
        else{
            consoleService.authorErrorInsert("");
        }
    }

    public void update() {
        Optional<Author> author = consoleService.authorUpdate();
        if (author.isPresent()){
                authorRepository.save(author.get());
        }
        else{
            consoleService.authorErrorUpdate();
        }
    }
    public void delete() {
        Optional<Author> author = authorRepository.findById(consoleService.authorDelete());

        if (author.get().getID() != ""){
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
