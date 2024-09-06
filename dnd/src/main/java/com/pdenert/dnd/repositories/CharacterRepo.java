package com.pdenert.dnd.repositories;


import com.pdenert.dnd.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepo extends JpaRepository<Character, Integer> {
}
