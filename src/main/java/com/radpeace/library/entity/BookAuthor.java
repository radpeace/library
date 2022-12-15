//package com.radpeace.library.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="author")
//@Getter
//@Setter
//public class BookAuthor {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name="book_id")
//    private Book bookId;
//
//    @ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name="author_id")
//    private Author authorId;
//
//    public BookAuthor() {
//    }
//
//}
