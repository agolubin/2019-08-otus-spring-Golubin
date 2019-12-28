package ru.otus.spring08.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Author")
public class Author {
    @Id
    private String iD;

    @Field(name = "name")
    private String name;

    @Field(name = "surName")
    private String surName;

    public String getID () {
        return iD;
    }

    public void setID (String ID) {
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

    public Author(String iD, String name, String surName) {
        this.iD       = iD;
        this.name     = name;
        this.surName  = surName;
    }
}
