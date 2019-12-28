package ru.otus.spring08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring08.exceptions.CommentsExistException;
import ru.otus.spring08.repository.BookRepository;
import ru.otus.spring08.repository.CommentsRepository;
import ru.otus.spring08.domain.Book;
import ru.otus.spring08.domain.Comments;

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
        String bookid = consoleService.bookBookID();
        Optional<Book> book   = bookRepository.findById(bookid);

        if (!book.isPresent()){
            consoleService.printError("book.errorBookExist");
            return;
        }

        String textComment    = consoleService.commentsText();

        Comments comments = new Comments("", book.get(), textComment);
            commentsRepository.save(comments);
    }

    public void update() {
        String commentsID = consoleService.commentsCommentsID();
        Optional<Comments> comments2 = commentsRepository.findById(commentsID);

        if (!comments2.isPresent()){
            consoleService.printError("comments.errorCommentsExist");
            return;
        }

        String bookid = consoleService.bookBookID();
        Optional<Book> book = bookRepository.findById(bookid);

        if (!book.isPresent()){
            consoleService.printError("book.errorBookExist");
            return;
        }

        String textComment    = consoleService.commentsText();

        Comments comments = new Comments("", book.get(), textComment);

        commentsRepository.save(comments);
    }



    public void delete() {
        String commentsID = consoleService.commentsCommentsID();
        Optional<Comments> comments = commentsRepository.findById(commentsID);

        if (comments.get().getID() != ""){
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
