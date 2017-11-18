package com.example.verma.bootapp.repository;

import com.example.verma.bootapp.dto.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Created by SANJIT on 03/11/17.
 */

public interface ReaderRepository extends JpaRepository<Reader,Long> {
    Reader findByuserName(String userName);
}
