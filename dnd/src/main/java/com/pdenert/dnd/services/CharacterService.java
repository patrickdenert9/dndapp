package com.pdenert.dnd.services;

import com.pdenert.dnd.models.Character;
import com.pdenert.dnd.models.User;
import com.pdenert.dnd.repositories.CharacterRepo;

import java.util.List;

public interface CharacterService {


    Character addCharacter(Character character);

    List<Character> getAllCharacters(User user);
}
