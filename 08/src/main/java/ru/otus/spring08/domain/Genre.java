package ru.otus.spring08.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection="Genre")
public class Genre {
    @Id
    private String iD;

    @Field(name = "name")
    private String name;

    public String getID () {
        return iD;
    }

    public void setID (String iD) {
        this.iD = iD;
    }

    public String getName () {
        return name;
    }

    public Genre() {
    }

    public void setName (String name) {
        this.name = name;
    }
    public Genre(String iD, String name) {
        this.iD   = iD;
        this.name = name;
    }
}
