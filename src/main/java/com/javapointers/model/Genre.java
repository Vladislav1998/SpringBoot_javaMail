package com.javapointers.model;

import javax.persistence.Entity;
import javax.persistence.*;
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreID;
    @Column
    private String title;

    @Override
    public String toString() {
        return
                "genreID=" + genreID +
                ",Name_genre=" + title + '\'';
    }

    public Genre(){

    }

    public Genre(String title) {
        this.title = title;
    }

    public Integer getGenreID() {
        return genreID;
    }

    public void setGenreID(Integer genreID) {
        this.genreID = genreID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
