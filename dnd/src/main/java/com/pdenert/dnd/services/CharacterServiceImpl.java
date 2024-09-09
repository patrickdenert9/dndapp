package com.pdenert.dnd.services;

import com.pdenert.dnd.models.Character;
import com.pdenert.dnd.models.User;
import com.pdenert.dnd.models.dtos.CharacterInfoDto;
import com.pdenert.dnd.repositories.CharacterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService{

    private CharacterRepo characterRepo;

    // constructor inject dependencies
    @Autowired
    public CharacterServiceImpl(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    /**
     * add character to db
     *
     * @param character character info to save in db
     * @return character saved in db
     */
    @Override
    public Character addCharacter(Character character) {
        return characterRepo.save(character);
    }

    /**
     * retrieves all characters for given user and removes user details from response
     *
     * @param user user to retrieve chars for
     * @return list of all char belonging to user
     */
    @Override
    public List<CharacterInfoDto> getAllCharacters(User user){
        List<Character> characters = characterRepo.findAllByUser(user);

        //for(Character character: characters) {                              // remove user info from response
        //    character.setUser(null);
        //}

        return convertDto(characters);
    }

    /**
     * retrieves all characters for given user and removes user details from response
     *
     * @param characters char to return
     * @return list of all char belonging to user
     */
    @Override
    public List<CharacterInfoDto> convertDto(List<Character> characters) {
        List<CharacterInfoDto> characterInfo = new ArrayList<>();
        for(Character character: characters) {
            character.setUser(null);
            characterInfo.add(new CharacterInfoDto(character));
        }
        return characterInfo;
    }
}
