package com.radpeace.library.repository;

import com.radpeace.library.entity.Author;
import com.radpeace.library.entity.Book;
import com.radpeace.library.entity.Queue;
import com.radpeace.library.service.QueueService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {

    Queue findFirstByBookId (Book id);
    Queue findByBookId (Book id);
}
