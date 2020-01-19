package ru.otus.spring10.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "Author")
public class Author {
    @Id
    @JsonProperty(value = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iD;

    @JsonProperty(value = "name")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonProperty(value = "surName")
    @Column(name = "surName", nullable = false, unique = true)
    private String surName;

    public Long getID () {
        return iD;
    }

    public void setID (Long ID) {
        this.iD = ID;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getSurName () {
        return surName;
    }

    public void setSurName (String surName) {
        this.surName = surName;
    }

    public Author() {
    }

    public Author(long iD, String name, String surName) {
        this.iD       = iD;
        this.name     = name;
        this.surName  = surName;
    }
}
