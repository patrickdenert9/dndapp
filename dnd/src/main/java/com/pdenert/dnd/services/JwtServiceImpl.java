package com.pdenert.dnd.services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    public static final long EXPIRATION = 12 * 60 * 60 * 1000;          // expiration = 12 hours


    @Value("${jwt.secret}")
    private String secretKey;                                           // get secretkey from application.yml


    /**
     * Calls private JWT token generator with a subject of userid
     *
     * @param username The username of a User.
     * @return jwt token as String.
     */
    @Override
    public String generateJwt(String username) {
        return generateJwtHelper(username);
    }

    /**
     * Generates JWT token with a subject of userid
     *
     * @param username The username of a User.
     * @return jwt token as String.
     */
    private String generateJwtHelper(String username) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));   // create key from app.yml

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }

    /**
     * Get user from the token
     *
     * @param token the jwt token from request
     * @return User if exists or null if not.
     */
//    @Override
//    public User getUserFromToken(String token) throws UnauthorizedException {
//        int id = verifyJwt(token);
//        if(id == 0){
//            throw new UnauthorizedException("Authentication Failed");
//        } else if(id == -1) {
//            throw new UnauthorizedException("Token Expired");
//        } else{
//            return us.getUser(id);
//        }
//    }

    /**
     * Verify JWT token
     *
     * @param token the jwt token from request
     * @return Authentication Failed  = Auth failed (token is not valid)
     * Expired Token  = expired token
     * username = username of user
     */
    @Override
    public String verifyJwt(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));   // create key from app.yml
        token = token.split(" ")[1].trim();                                      //remove "Bearer" from token header
        Claims body;
        try {
            body = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            return null;
        }
        if(body.getExpiration().getTime() < System.currentTimeMillis()) {
            return "Expired";
        }

        return body.getSubject();
    }

}