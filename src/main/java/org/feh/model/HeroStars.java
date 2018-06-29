package org.feh.model;

public class HeroStars {
    private Integer id;

    private Integer herosId;

    private Boolean stars;

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
}