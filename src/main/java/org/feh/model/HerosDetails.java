package org.feh.model;

public class HerosDetails {
    private Integer id;

    private Integer herosStarsId;

    private Integer hp;

    private String herosDetailscol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHerosStarsId() {
        return herosStarsId;
    }

    public void setHerosStarsId(Integer herosStarsId) {
        this.herosStarsId = herosStarsId;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public String getHerosDetailscol() {
        return herosDetailscol;
    }

    public void setHerosDetailscol(String herosDetailscol) {
        this.herosDetailscol = herosDetailscol;
    }
}