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
    public CharacterInfoDto addCharacter(CharacterInfoDto character) {
        Character newCharacter = characterRepo.save(character.getCharacter());
        List<String> skills = character.getSkills();

        // Save skills in junction table
        for(String skill: skills) {
            characterRepo.saveSkill(skill, newCharacter.getId());
        }

        newCharacter.setUser(null);
        return new CharacterInfoDto(newCharacter, characterRepo.getSkillsByCharacterId(newCharacter.getId()));
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
            List<String> skills = characterRepo.getSkillsByCharacterId(character.getId());
            characterInfo.add(new CharacterInfoDto(character, skills));
        }
        return characterInfo;
    }

}
