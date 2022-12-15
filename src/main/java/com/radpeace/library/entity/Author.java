package com.radpeace.library.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="author")
@NoArgsConstructor
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "authorId", cascade = CascadeType.ALL)
//    private List<BookAuthor> bookAuthors;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

}
