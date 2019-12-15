package ru.otus.spring06.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring06.service.CommentsService;

@ShellComponent
@RequiredArgsConstructor
public class CommentsShell {

    private final CommentsService commentsService;

    @ShellMethod(value = "Comments insert", key = {"Comments insert", "ci"})
    public void commentsInsert() {
        commentsService.insert();
    }

    @ShellMethod(value = "Comments update", key = {"Comments update", "cu"})
    public void commentsUpdate() {
        commentsService.update();
    }

    @ShellMethod(value = "Comments delete", key = {"Comments delete", "cd"})
    public void commentsDelete() {
        commentsService.delete();
    }

    @ShellMethod(value = "Comments find all", key = {"Comments find all", "cfa"})
    public void commentsFindAll() {
        commentsService.findAll();
    }

}
