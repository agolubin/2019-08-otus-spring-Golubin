package ru.otus.spring06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring06.exceptions.CommentsExistException;
import ru.otus.spring06.repository.BookRepository;
import ru.otus.spring06.repository.CommentsRepository;
import ru.otus.spring06.domain.Book;
import ru.otus.spring06.domain.Comments;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final ConsoleService consoleService;
    private final BookRepository bookRepository;
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServiceImpl(ConsoleService consoleService, CommentsRepository commentsRepository, BookRepository bookRepository) {
        this.consoleService     = consoleService;
        this.commentsRepository = commentsRepository;
        this.bookRepository     = bookRepository;
    }

    public void insert() {
        Long bookid = consoleService.bookBookID();
        Optional<Book> book   = bookRepository.getByID(bookid);

        if (!book.isPresent()){
            consoleService.printError("book.errorBookExist");
            return;
        }

        String textComment    = consoleService.commentsText();

        Comments comments = new Comments(0L, book.get(), textComment);
        try {
            commentsRepository.insert(comments);
        } catch (CommentsExistException e) {
            consoleService.printError("comments.errorInsert");
        }
    }

    public void update() {
        Long commentsID = consoleService.commentsCommentsID();
        Optional<Comments> comments2 = commentsRepository.getByID(commentsID);

        if (!comments2.isPresent()){
            consoleService.printError("comments.errorCommentsExist");
            return;
        }

        Long bookid = consoleService.bookBookID();
        Optional<Book> book = bookRepository.getByID(bookid);

        if (!book.isPresent()){
            consoleService.printError("book.errorBookExist");
            return;
        }

        String textComment    = consoleService.commentsText();

        Comments comments = new Comments(0L, book.get(), textComment);

        try {
            commentsRepository.update(comments);
        } catch (CommentsExistException e) {
            consoleService.printError("comments.errorUpdate");
        }
    }



    public void delete() {
        Long commentsID = consoleService.commentsCommentsID();
        Optional<Comments> comments = commentsRepository.getByID(commentsID);

        if (comments.get().getID() > 0){
            try {
                commentsRepository.delete(comments.get());
            }
            catch (Exception e){
                consoleService.printError("comments.errorDelete");
            }
        }
        else{
            consoleService.printError("comments.errorCommentsExist");
        }
    }

    public void findAll() {
        List<Comments> list = commentsRepository.findAll();
         consoleService.commentsFindAll(list);
    }


}