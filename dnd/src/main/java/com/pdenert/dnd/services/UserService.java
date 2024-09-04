package com.pdenert.dnd.services;

import com.pdenert.dnd.models.User;

public interface UserService {
    User addUser(User user);

    User verify(User user);

    User getUser(int id);
}
