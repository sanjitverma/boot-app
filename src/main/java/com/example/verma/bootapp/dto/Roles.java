package com.example.verma.bootapp.dto;


import javax.persistence.*;
import java.util.Collection;

/**
 * Created by SANJIT on 18/11/17.
 */
@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    Collection<Reader> readers;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    Collection<Privilege> privileges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<Reader> getReaders() {
        return readers;
    }

    public void setReaders(Collection<Reader> readers) {
        this.readers = readers;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
}
