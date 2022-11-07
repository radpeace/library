package com.radpeace.library.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="book")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "vendor")
    private Integer vendor;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "bookId", cascade = CascadeType.ALL)
    private List<IssuedBook> issuedBooks;

    @OneToMany(mappedBy = "genreBookId", cascade = CascadeType.ALL)
    private List<Genre> genres;

    @OneToMany(mappedBy = "authorBookId", cascade = CascadeType.ALL)
    private List<Author> authors;

    @OneToMany(mappedBy = "commentBookId", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Book() {
    }
}
