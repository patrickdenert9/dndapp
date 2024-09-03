package com.pdenert.dnd.services;

import com.pdenert.dnd.models.User;
import com.pdenert.dnd.models.enums.Role;
import com.pdenert.dnd.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User addUser(User user) {
        user.setRole(Role.user);            // set role to user so someone cant try to add an admin in the app
                                            // admin should only be added in the db manually
        return userRepo.save(user);
    }
}
