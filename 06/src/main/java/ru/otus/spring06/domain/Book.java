package ru.otus.spring06.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Book")
@NamedEntityGraph(name = "BookWithAuthorAndGenre",
        attributeNodes = {@NamedAttributeNode(value = "author"),
                @NamedAttributeNode(value = "genre")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  iD;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "Author_ID")
    private Author author;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE )
    @JoinColumn(name = "Genre_ID")
    private Genre genre;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Long getID () {
        return iD;
    }

    public void setID (Long ID) {
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

    public Book(Long iD, Author author, Genre genre, String name) {
        this.iD       = iD;
        this.author   = author;
        this.genre    = genre;
        this.name     = name;

    }

}
