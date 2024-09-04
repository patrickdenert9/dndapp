package com.pdenert.dnd.controllers;


import com.pdenert.dnd.models.User;
import com.pdenert.dnd.services.JwtService;
import com.pdenert.dnd.services.JwtServiceImpl;
import com.pdenert.dnd.services.UserService;
import com.pdenert.dnd.services.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    JwtService jwtService;

    @Autowired
    public UserController(UserServiceImpl userService, JwtServiceImpl jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        user = userService.verify(user);                                        // verify user and retrieve real user info
        String jwt = jwtService.generateJwt(user.getId());                      // use real user id to gen jwt
        return ResponseEntity.status(200).body(jwt);
    }

    @GetMapping
    public String test() {
        return "Hello";
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
