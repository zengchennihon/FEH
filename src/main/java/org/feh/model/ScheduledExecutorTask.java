package org.feh.model;

public class ScheduledExecutorTask {
    private Integer id;

    private String clazz;

    private Integer firstH;

    private Integer firstM;

    private Integer firstS;

    private Integer cycleH;

    private Integer cycleM;

    private Integer cycleS;

    private Boolean enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Integer getFirstH() {
        return firstH;
    }

    public void setFirstH(Integer firstH) {
        this.firstH = firstH;
    }

    public Integer getFirstM() {
        return firstM;
    }

    public void setFirstM(Integer firstM) {
        this.firstM = firstM;
    }

    public Integer getFirstS() {
        return firstS;
    }

    public void setFirstS(Integer firstS) {
        this.firstS = firstS;
    }

    public Integer getCycleH() {
        return cycleH;
    }

    public void setCycleH(Integer cycleH) {
        this.cycleH = cycleH;
    }

    public Integer getCycleM() {
        return cycleM;
    }

    public void setCycleM(Integer cycleM) {
        this.cycleM = cycleM;
    }

    public Integer getCycleS() {
        return cycleS;
    }

    public void setCycleS(Integer cycleS) {
        this.cycleS = cycleS;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}