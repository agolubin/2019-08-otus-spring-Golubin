package ru.otus.spring06.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iD;

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
