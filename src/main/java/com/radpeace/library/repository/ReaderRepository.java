package com.radpeace.library.repository;

import com.radpeace.library.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {

    Reader findByName (String name);

    Reader findById (int readerId);
}
