package com.radpeace.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="book")
@Getter
@Setter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "bookId", cascade = CascadeType.ALL)
    private List<IssuedBook> issuedBooks;

    @JsonIgnore
    @OneToMany(mappedBy = "genreBookId", cascade = CascadeType.ALL)
    private List<GenreEntity> genres;

    @OneToMany(mappedBy = "authorBookId", cascade = CascadeType.ALL)
    private List<Author> authors;

    @OneToMany(mappedBy = "commentBookId", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="library_id")
    private Library libraryId;

    public BookEntity() {
    }
}
