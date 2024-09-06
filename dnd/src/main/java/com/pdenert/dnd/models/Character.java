package com.pdenert.dnd.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;

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

    public Character() {
    }

    public Character(int id, User user, String name, int level, String charClass, String race, int currentHp, int maxHp, int str, int dex, int con, int ints, int wis, int cha, int speed, String vision) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.level = level;
        this.charClass = charClass;
        this.race = race;
        this.currentHp = currentHp;
        this.maxHp = maxHp;
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.ints = ints;
        this.wis = wis;
        this.cha = cha;
        this.speed = speed;
        this.vision = vision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getInts() {
        return ints;
    }

    public void setInts(int ints) {
        this.ints = ints;
    }

    public int getWis() {
        return wis;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getCha() {
        return cha;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return id == character.id && level == character.level && currentHp == character.currentHp && maxHp == character.maxHp && str == character.str && dex == character.dex && con == character.con && ints == character.ints && wis == character.wis && cha == character.cha && speed == character.speed && Objects.equals(user, character.user) && Objects.equals(name, character.name) && Objects.equals(charClass, character.charClass) && Objects.equals(race, character.race) && Objects.equals(vision, character.vision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, level, charClass, race, currentHp, maxHp, str, dex, con, ints, wis, cha, speed, vision);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", charClass='" + charClass + '\'' +
                ", race='" + race + '\'' +
                ", currentHp=" + currentHp +
                ", maxHp=" + maxHp +
                ", str=" + str +
                ", dex=" + dex +
                ", con=" + con +
                ", ints=" + ints +
                ", wis=" + wis +
                ", cha=" + cha +
                ", speed=" + speed +
                ", vision='" + vision + '\'' +
                '}';
    }
}
