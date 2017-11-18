package com.example.verma.bootapp.repository;

import com.example.verma.bootapp.dto.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by SANJIT on 18/11/17.
 */
public interface RoleRepository extends JpaRepository<Roles, Long> {
    List<Roles> findByReaders(String userName);
}
