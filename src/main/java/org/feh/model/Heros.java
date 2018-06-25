package org.feh.model;

public class Heros {
    private Integer id;

    private Byte gender;

    private String routine;

    private String battle;

    private String upanishad;

    private String seriousInjury;

    private byte[] headPortrait;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public byte[] getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(byte[] headPortrait) {
        this.headPortrait = headPortrait;
    }
}