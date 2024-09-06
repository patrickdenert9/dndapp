package com.pdenert.dnd.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "characters", schema = "dnd")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    @JsonProperty(value = "charId")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(value = "userId")
    private User user;

    @Column(name = "name")
    @JsonProperty(value = "name")
    private String name;

    @Column(name = "level")
    @JsonProperty(value = "level")
    private int level;

    @Column(name = "class")
    @JsonProperty(value = "class")
    private String charClass;

    @Column(name = "race")
    @JsonProperty(value = "race")
    private String race;

    @Column(name = "current_hp")
    @JsonProperty(value = "currentHp")
    private int currentHp;

    @Column(name = "max_hp")
    @JsonProperty(value = "maxHp")
    private int maxHp;

    @Column(name = "str")
    @JsonProperty(value = "str")
    private int str;

    @Column(name = "dex")
    @JsonProperty(value = "dex")
    private int dex;

    @Column(name = "con")
    @JsonProperty(value = "con")
    private int con;

    @Column(name = "int")
    @JsonProperty(value = "int")
    private int ints;

    @Column(name = "wis")
    @JsonProperty(value = "wis")
    private int wis;

    @Column(name = "cha")
    @JsonProperty(value = "cha")
    private int cha;

    @Column(name = "speed")
    @JsonProperty(value = "speed")
    private int speed;

    @Column(name = "vision")
    @JsonProperty(value = "vision")
    private String vision;
}
