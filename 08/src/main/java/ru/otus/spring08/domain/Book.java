package ru.otus.spring08.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "Book")
public class Book {

    @Id
    private String  iD;

    //@ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @Field(name = "Author_ID")
    private Author author;

    //@ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE )
    @Field(name = "Genre_ID")
    private Genre genre;

    @Field(name = "name")
    private String name;

    public String getID () {
        return iD;
    }

    public void setID (String ID) {
        this.iD = ID;
    }

    public Genre getGenre () {
        return genre;
    }

    public void setGenre (Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor () {
        return author;
    }

    public void setAuthor (Author author) {
        this.author = author;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Book() {
    }

    public Book(String iD, Author author, Genre genre, String name) {
        this.iD       = iD;
        this.author   = author;
        this.genre    = genre;
        this.name     = name;

    }

}
