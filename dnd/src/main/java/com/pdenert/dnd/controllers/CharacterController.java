package com.pdenert.dnd.controllers;

import com.pdenert.dnd.models.Character;
import com.pdenert.dnd.models.User;
import com.pdenert.dnd.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/characters")
@RestController
public class CharacterController {

    private CharacterService characterService;
    private UserService userService;

    @Autowired
    public CharacterController(CharacterServiceImpl characterService, UserServiceImpl userDetailsService) {
        this.characterService = characterService;
        this.userService = userDetailsService;
    }

    // @AuthenticationPrincipal gets userdetails from Spring Security
    @PostMapping("/new")
    public ResponseEntity<Character> addCharacter(@RequestBody Character character, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUser(userDetails.getUsername());                                 // get user from db
        character.setUser(user);                                                                    // set user_id in character
        return ResponseEntity.status(200).body(characterService.addCharacter(character));
    }

    @GetMapping
    public ResponseEntity<List<Character>> getCharacters(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUser(userDetails.getUsername());                                 // get user from db
        return ResponseEntity.status(200).body(characterService.getAllCharacters(user));
    }
}
