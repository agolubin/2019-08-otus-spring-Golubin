package ru.otus.spring07.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Comments")
@NamedEntityGraph(name = "CommetsWithBook",
        attributeNodes = {@NamedAttributeNode(value = "book")})
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  iD;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "Book_ID")
    private Book book;

    @Column(name = "textComment", nullable = false, unique = true)
    private String textComment;

    public Long getID () {
        return iD;
    }

    public void setID (Long iD) {
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

    public Comments(Long iD, Book book, String textComment) {
        this.iD          = iD;
        this.book        = book;
        this.textComment = textComment;

    }

}
