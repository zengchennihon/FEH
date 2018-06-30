package org.feh.model;

public class HeroStars {
    private Integer id;

    private Integer heroId;

    private Byte stars;

    private Boolean equipment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeroId() {
        return heroId;
    }

    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }

    public Byte getStars() {
        return stars;
    }

    public void setStars(Byte stars) {
        this.stars = stars;
    }

    public Boolean getEquipment() {
        return equipment;
    }

    public void setEquipment(Boolean equipment) {
        this.equipment = equipment;
    }
}