package com.pdenert.dnd.services;

import com.pdenert.dnd.models.Character;
import com.pdenert.dnd.models.User;
import com.pdenert.dnd.models.dtos.CharacterInfoDto;

import java.util.List;

public interface CharacterService {


    CharacterInfoDto addCharacter(Character character);

    List<CharacterInfoDto> getAllCharacters(User user);


    List<CharacterInfoDto> convertDto(List<Character> characters);
}
