package com.radpeace.library.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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
    private Book bookId;

    @Column(name = "date_issue")
    private LocalDate dateIssue;

    @Column(name = "date_return")
    private LocalDate dateReturn;

    public IssuedBook() {
    }

    public IssuedBook(Reader readerId, Book bookId, LocalDate dateIssue) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.dateIssue = dateIssue;
    }
}
