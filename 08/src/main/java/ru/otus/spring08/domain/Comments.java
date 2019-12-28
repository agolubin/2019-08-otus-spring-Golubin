package ru.otus.spring08.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Comments")
public class Comments {

    @Id
    private String  iD;

    @DBRef
    private Book book;

    @Field(name = "textComment")
    private String textComment;

    public String getID () {
        return iD;
    }

    public void setID (String iD) {
        this.iD = iD;
    }

    public Book getBook () {
        return book;
    }

    public void setBook (Book book) {
        this.book = book;
    }

    public String getTextComment () {
        return textComment;
    }

    public void setTextComment (String textComment) {
        this.textComment = textComment;
    }

    public Comments() {
    }

    public Comments(String iD, Book book, String textComment) {
        this.iD          = iD;
        this.book        = book;
        this.textComment = textComment;

    }

}
