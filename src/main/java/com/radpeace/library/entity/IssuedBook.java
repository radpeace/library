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

//    @JsonBackReference
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="reader_id")
    private Reader readerId;

//    @JsonBackReference
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="book_id")
    private Book bookId;

    @Column(name = "date_issue")
    private LocalDate dateIssue;

    @Column(name = "required_date_return")
    private LocalDate requiredDateReturn;

    @Column(name = "date_return")
    private LocalDate dateReturn;

    public IssuedBook() {
    }

}
