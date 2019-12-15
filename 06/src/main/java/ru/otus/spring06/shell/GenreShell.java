package ru.otus.spring06.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring06.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {


    private final GenreService genreService;

    @ShellMethod(value = "Genre insert", key = {"Genre insert", "gi"})
    public void genreInsert() {
        genreService.insert();
    }

    @ShellMethod(value = "Genre update", key = {"Genre update", "gu"})
    public void genreUpdate() {
        genreService.update();
    }

    @ShellMethod(value = "Genre update", key = {"Genre delete", "gd"})
    public void genreDelete() {
        genreService.delete();
    }

    @ShellMethod(value = "Genre find all", key = {"Genre find all", "gfa"})
    public void GenreFindAll() {
        genreService.findAll();
    }

}