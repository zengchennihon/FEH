package org.feh.model;

public class Hero {
    private Integer id;

    private String aid;

    private Byte gender;

    private String routine;

    private String battle;

    private String upanishad;

    private String seriousInjury;

    private String movementType;

    private byte[] headPortrait;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getRoutine() {
        return routine;
    }

    public void setRoutine(String routine) {
        this.routine = routine;
    }

    public String getBattle() {
        return battle;
    }

    public void setBattle(String battle) {
        this.battle = battle;
    }

    public String getUpanishad() {
        return upanishad;
    }

    public void setUpanishad(String upanishad) {
        this.upanishad = upanishad;
    }

    public String getSeriousInjury() {
        return seriousInjury;
    }

    public void setSeriousInjury(String seriousInjury) {
        this.seriousInjury = seriousInjury;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public byte[] getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(byte[] headPortrait) {
        this.headPortrait = headPortrait;
    }
}