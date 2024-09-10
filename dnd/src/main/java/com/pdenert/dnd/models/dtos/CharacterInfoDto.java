package com.pdenert.dnd.models.dtos;

import com.pdenert.dnd.models.Character;

import java.util.ArrayList;
import java.util.List;


//returns all info to frontend at once to save transactions
public class CharacterInfoDto {
    private Character character;
    private List<String> skills;
    // skill profs
    // items
    // spells
    // etc


    public CharacterInfoDto(Character character, List<String> skills) {
        this.character = character;
        this.skills = skills;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
