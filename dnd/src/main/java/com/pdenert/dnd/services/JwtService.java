package com.pdenert.dnd.services;

public interface JwtService {



    String generateJwt(String username);

    //User getUserFromToken(String token) throws UnauthorizedException;

    String verifyJwt(String token);
}
