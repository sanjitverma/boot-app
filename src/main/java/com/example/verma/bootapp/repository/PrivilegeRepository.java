package com.example.verma.bootapp.repository;

import com.example.verma.bootapp.dto.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by SANJIT on 18/11/17.
 */

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    List<Privilege> findByRoles(String roleName);
}
