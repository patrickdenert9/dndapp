package com.pdenert.dnd.services;


import com.pdenert.dnd.models.User;
import com.pdenert.dnd.models.UserPrincipal;
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
        return new UserPrincipal(user);
    }
}
