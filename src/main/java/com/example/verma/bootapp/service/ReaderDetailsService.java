package com.example.verma.bootapp.service;

import com.example.verma.bootapp.dto.Privilege;
import com.example.verma.bootapp.dto.Reader;
import com.example.verma.bootapp.dto.Roles;
import com.example.verma.bootapp.repository.ReaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by SANJIT on 18/11/17.
 */
@Service("userDetailsService")
@Transactional
public class ReaderDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(ReaderDetailsService.class);

    @Autowired(required = true)
    ReaderRepository readerRepository;

    @Override
    public UserDetails loadUserByUsername(String readerName) throws UsernameNotFoundException {

        Reader reader = readerRepository.findByuserName(readerName);
        reader.getFullname();

        System.out.println("############################################");
        System.out.println("Reader found with user name : " + readerName);
        System.out.println(reader.toString());


        return new org.springframework.security.core.userdetails.User(
                reader.getUsername(), reader.getPassword(), true, true, true,
                true, getAuthoritiesNew(reader.getRoles()));

    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Roles> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }


    private List<String> getPrivileges(Collection<Roles> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Roles role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getPrivilegeName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesNew(Collection<Roles> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            role.getPrivileges().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getPrivilegeName()))
                    .forEach(authorities::add);
        }

        return authorities;
    }

}
