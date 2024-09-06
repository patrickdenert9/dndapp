package com.pdenert.dnd.services;

import com.pdenert.dnd.models.User;
import com.pdenert.dnd.models.enums.Role;
import com.pdenert.dnd.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);   // strength = 12

    @Override
    public User addUser(User user) {
        user.setRole(Role.user);            // set role to user so someone cant try to add an admin in the app
                                            // admin should only be added in the db manually

        user.setPassword(bcrypt.encode(user.getPassword()));    // hash password with bcrypt
        return userRepo.save(user);
    }

    @Override
    public User verify(User user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authentication.isAuthenticated()) {
            return userRepo.findByUsername(user.getUsername());
        } else {
            return null;
        }
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }


}
