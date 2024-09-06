package com.pdenert.dnd.repositories;


import com.pdenert.dnd.models.Character;
import com.pdenert.dnd.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepo extends JpaRepository<Character, Integer> {
    Character findByUser(User user);

    List<Character> findAllByUser(User user);
}
