package com.example.OnLib.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "author", length = 50)
    private String author;

    @Column(name = "publisher", length = 50)
    private String publisher;

    @Column(name = "yearOfPublication")
    private Integer yearOfPublication;

    @Column(name = "numberOfPages")
    private Integer numberOfPages;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "genre")
    private String genre;
    @Column(name = "description")
    private String description;
    @Column(name = "text")
    private String text;

}