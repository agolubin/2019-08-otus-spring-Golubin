package ru.otus.spring07.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring07.service.AuthorService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorService authorService;

    @ShellMethod(value = "Author insert", key = {"Author insert", "ai"})
    public void authorInsert() {
        authorService.insert();
    }

    @ShellMethod(value = "Author update", key = {"Author update", "au"})
    public void authorUpdate() {
        authorService.update();
    }

    @ShellMethod(value = "Author update", key = {"Author delete", "ad"})
    public void authorDelete() {
        authorService.delete();
    }

    @ShellMethod(value = "Author find all", key = {"Author find all", "afa"})
    public void authorFindAll() {
        authorService.findAll();
    }

}
