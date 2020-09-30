package com.fcicb.domain;


public class Course {

    private String code;
    private String name;
    private int hours;
    private int level;
    private String description;
    private int addedBy;

    public Course() {

    }

    public Course( String code, String name, int hours, int level, String description,int addedBy) {

        this.code = code;
        this.name = name;
        this.hours = hours;
        this.level = level;
        this.description = description;
        this.addedBy = addedBy;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(int addedBy) {
        this.addedBy = addedBy;
    }
}
