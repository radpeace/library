package com.radpeace.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="reader")
@Getter
@Setter
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "phone")
    private String phone;

//    @OneToMany(mappedBy = "readerId", cascade = CascadeType.ALL)
//    private List<IssuedBook> issuedBooks;

    @OneToMany(mappedBy = "commentReaderId", cascade = CascadeType.ALL)
    private List<Comment> comments;

//    @Column(name = "permit")
//    private boolean permit;

    public Reader() {
    }

}
