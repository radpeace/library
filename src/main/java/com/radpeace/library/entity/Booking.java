package com.radpeace.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="booking")
@Getter
@Setter
public class Booking {

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
}
