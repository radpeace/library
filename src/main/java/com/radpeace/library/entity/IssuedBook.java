package com.radpeace.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="issued_book")
@Getter
@Setter
public class IssuedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="reader_id")
    private Reader readerId;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="book_id")
    private BookEntity bookId;

    @Column(name = "date_issue")
    private LocalDate dateIssue;

    @Column(name = "date_return")
    private LocalDate dateReturn;

    public IssuedBook() {
    }

    public IssuedBook(Reader readerId, BookEntity bookId, LocalDate dateIssue) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.dateIssue = dateIssue;
    }
}
