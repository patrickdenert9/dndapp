package com.pdenert.dnd.repositories;


import com.pdenert.dnd.models.Character;
import com.pdenert.dnd.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepo extends JpaRepository<Character, Integer> {
    Character findByUser(User user);

    List<Character> findAllByUser(User user);

    // Get skills by character id
    @Query(value = "SELECT dnd.skills.skill FROM dnd.skills  \n" +
            "INNER JOIN dnd.profficient_skills ps on ps.skill = dnd.skills.skill \n" +
            "INNER JOIN dnd.characters c on c.character_id = ps.character_id \n" +
            "WHERE c.character_id = :id;", nativeQuery = true)
    List<String> getSkillsByCharacterId(int id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO dnd.profficient_skills (character_id, skill) VALUES\n" +
            "(:id, :skill);", nativeQuery = true)
    void saveSkill(String skill, int id);
}
