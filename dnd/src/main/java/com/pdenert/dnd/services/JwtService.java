package com.pdenert.dnd.services;

import com.pdenert.dnd.models.User;

public interface JwtService {



    String generateJwt(Integer id);

    User getUserFromToken(String token) throws UnauthorizedException;

    int verifyJwt(String token);
}
