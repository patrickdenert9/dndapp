package com.pdenert.dnd.models.dtos;

import com.pdenert.dnd.models.enums.Role;

public class UserDto {
    private String email;
    private String username;

    public UserDto(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
