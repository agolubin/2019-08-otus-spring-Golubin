package ru.otus.spring06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring06.Exceptions.AuthorExistException;
import ru.otus.spring06.Repository.AuthorRepository;
import ru.otus.spring06.domain.Author;

import java.util.ArrayList;
import java.util.List;

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
        Author author = consoleService.authorInsert();
        if (author != null){
            try {
                authorRepository.insert(author);
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
            try {
                authorRepository.update(author);
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
        Author author = authorRepository.getByID(consoleService.authorDelete());

        if (author.getID() > 0){
            try {
                authorRepository.delete(author);
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
