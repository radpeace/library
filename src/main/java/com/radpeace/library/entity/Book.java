package com.radpeace.library.entity;

import lombok.Getter;
import lombok.Setter;

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
    private Long id;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

//    @OneToMany(mappedBy = "bookId", cascade = CascadeType.ALL)
//    private List<IssuedBook> issuedBooks;

//    @JsonManagedReference
    @OneToMany(mappedBy = "genreBookId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Genre> genres;

//    @JsonManagedReference
    @OneToMany(mappedBy = "authorBookId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Author> authors;

//    @JsonManagedReference
    @OneToMany(mappedBy = "commentBookId", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="library_id")
    private Library libraryId;

    public Book() {
    }

//    public void addGenres(List<Genre> genres) {
//        genres.addAll(genres);
//        genres.forEach(genre -> genre.setGenreBookId(this));
//    }
//
//    public void deleteGenres() {
//        genres.clear();
//        genres.forEach(genre -> genre.setGenreBookId(null));
//    }

}
