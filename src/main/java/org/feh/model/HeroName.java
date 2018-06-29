package org.feh.model;

public class HeroName {
    private Integer id;

    private Integer herosId;

    private String nameCn;

    private String nameJp;

    private String nameEn;

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

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameJp() {
        return nameJp;
    }

    public void setNameJp(String nameJp) {
        this.nameJp = nameJp;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}