package com.example.verma.bootapp.repository;

import com.example.verma.bootapp.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by SANJIT on 31/10/17.
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);

}
