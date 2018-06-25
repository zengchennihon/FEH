package org.feh.model;

public class HerosStars {
    private Integer id;

    private Integer herosId;

    private Boolean stars;

    private String character;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHerosId() {
        return herosId;
    }

    public void setHerosId(Integer herosId) {
        this.herosId = herosId;
    }

    public Boolean getStars() {
        return stars;
    }

    public void setStars(Boolean stars) {
        this.stars = stars;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}