package com.pdenert.dnd.services;

import com.pdenert.dnd.models.Character;
import com.pdenert.dnd.models.User;
import com.pdenert.dnd.repositories.CharacterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService{

    private CharacterRepo characterRepo;

    @Autowired
    public CharacterServiceImpl(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    @Override
    public Character addCharacter(Character character) {
        return characterRepo.save(character);
    }

    @Override
    public List<Character> getAllCharacters(User user){
        List<Character> characters = characterRepo.findAllByUser(user);
        for(Character character: characters) {                              // remove user info from response
            character.setUser(null);
        }
        return characters;
    }
}
