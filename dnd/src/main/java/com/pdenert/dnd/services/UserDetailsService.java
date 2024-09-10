package com.pdenert.dnd.services;


import com.pdenert.dnd.models.User;
import com.pdenert.dnd.models.UserPrincipal;
import com.pdenert.dnd.models.enums.Role;
import com.pdenert.dnd.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepo repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        switch (user.getRole()) {                                                           // create user based on role
            case user:
                return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles("USER")
                        .build();
            case admin:
                System.out.println("Admin logged in");
                return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles("ADMIN")
                        .build();

        }

        return null;
    }
}
