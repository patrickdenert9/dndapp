package com.pdenert.dnd.services;

import com.pdenert.dnd.models.User;
import com.pdenert.dnd.models.dtos.UserDto;

public interface UserService {
    UserDto addUser(User user);

    User verify(User user);

    //User getUser(int id);

    User getUser(String username);
}
