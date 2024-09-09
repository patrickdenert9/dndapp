package com.pdenert.dnd.controllers;

import com.pdenert.dnd.models.Character;
import com.pdenert.dnd.models.User;
import com.pdenert.dnd.models.dtos.CharacterInfoDto;
import com.pdenert.dnd.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * endpoint to save char to user's account in db
     *
     * @param userDetails user to save char for
     * @param character char to save to db
     * @return saved char in db
     */
    @PostMapping("/new")
    public ResponseEntity<CharacterInfoDto> addCharacter(@RequestBody Character character, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUser(userDetails.getUsername());                                 // get user from db
        character.setUser(user);                                                                    // set user_id in character
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.addCharacter(character));
    }

    /**
     * endpoint to retrieves all characters for given user and removes user details from response
     * gets user from AuthProvider in spring security
     *
     * @param userDetails user to retrieve chars for
     * @return list of all char belonging to user
     */
    @GetMapping
    public ResponseEntity<List<CharacterInfoDto>> getCharacters(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUser(userDetails.getUsername());                                 // get user from db
        return ResponseEntity.status(200).body(characterService.getAllCharacters(user));
    }
}
