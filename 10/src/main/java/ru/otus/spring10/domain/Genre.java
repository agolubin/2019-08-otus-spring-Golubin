package ru.otus.spring10.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @JsonProperty(value = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iD;

    @JsonProperty(value = "name")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Long getID () {
        return iD;
    }

    public void setID (Long iD) {
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
    public Genre(long iD, String name) {
        this.iD   = iD;
        this.name = name;
    }
}
