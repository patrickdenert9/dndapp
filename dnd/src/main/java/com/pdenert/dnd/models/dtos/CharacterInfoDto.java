package com.pdenert.dnd.models.dtos;

import com.pdenert.dnd.models.Character;


//returns all info to frontend at once to save transactions
public class CharacterInfoDto {
    private Character character;
    // skill profs
    // items
    // spells
    // etc


    public CharacterInfoDto(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
