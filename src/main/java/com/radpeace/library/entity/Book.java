package com.radpeace.library.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "cover")
    private String cover;

    @Column(name = "file")
    private String file;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_type")
    private Type bookType;

//    @OneToMany(mappedBy = "bookId", cascade = CascadeType.ALL)
//    private List<IssuedBook> issuedBooks;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "genreBookId", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Genre> genres;

    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @ManyToMany/*(cascade = {CascadeType.PERSIST, CascadeType.MERGE})*/
    private Set<Author> authors = new HashSet<>();

    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany/*(cascade = {CascadeType.PERSIST, CascadeType.MERGE})*/
    private Set<Genre> genres = new HashSet<>();

    //    @JsonManagedReference
    @OneToMany(mappedBy = "commentBookId", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "library_id")
    private Library libraryId;

    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getBooks().remove(this);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getBooks().add(this);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getBooks().remove(this);
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
