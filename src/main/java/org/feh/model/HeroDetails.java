package org.feh.model;

public class HeroDetails {
    private Integer id;

    private Integer heroStarsId;

    private Integer level;

    private String heroCharacter;

    private Integer hp;

    private Integer atk;

    private Integer spd;

    private Integer def;

    private Integer res;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeroStarsId() {
        return heroStarsId;
    }

    public void setHeroStarsId(Integer heroStarsId) {
        this.heroStarsId = heroStarsId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getHeroCharacter() {
        return heroCharacter;
    }

    public void setHeroCharacter(String heroCharacter) {
        this.heroCharacter = heroCharacter;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAtk() {
        return atk;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public Integer getSpd() {
        return spd;
    }

    public void setSpd(Integer spd) {
        this.spd = spd;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }
}