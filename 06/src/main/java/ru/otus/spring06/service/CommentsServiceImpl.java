package ru.otus.spring06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring06.Exceptions.CommentsExistException;
import ru.otus.spring06.Repository.BookRepository;
import ru.otus.spring06.Repository.CommentsRepository;
import ru.otus.spring06.domain.Book;
import ru.otus.spring06.domain.Comments;

import java.util.List;

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
        Book book   = bookRepository.getByID(bookid);

        if (book == null){
            consoleService.printError("book.errorBookExist");
            return;
        }

        String textComment    = consoleService.commentsText();

        Comments comments = new Comments(0L, book, textComment);
        try {
            commentsRepository.insert(comments);
        } catch (CommentsExistException e) {
            consoleService.printError("comments.errorInsert");
        }
    }

    public void update() {
        Long commentsID = consoleService.commentsCommentsID();
        Comments comments = commentsRepository.getByID(commentsID);

        if (comments == null){
            consoleService.printError("comments.errorCommentsExist");
            return;
        }

        Long bookid = consoleService.bookBookID();
        Book book = bookRepository.getByID(bookid);

        if (book == null){
            consoleService.printError("book.errorBookExist");
            return;
        }

        String textComment    = consoleService.commentsText();

        comments.setTextComment(textComment);

        try {
            commentsRepository.update(comments);
        } catch (CommentsExistException e) {
            consoleService.printError("comments.errorUpdate");
        }
    }



    public void delete() {
        Long commentsID = consoleService.commentsCommentsID();
        Comments comments = commentsRepository.getByID(commentsID);

        if (comments.getID() > 0){
            try {
                commentsRepository.delete(comments);
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
