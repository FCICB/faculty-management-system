package com.fcicb.domain;


import java.util.Date;

public class Student {

    private int id;
    private String username;
    private float gpa;
    private Date birthDate;

    public enum  studentState { ACTIVATED, DEACTIVATED };
    private studentState state;
    private int level;
    private int completedHours;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private int deletedBy;
    private int addedBy;

    public Student(  String username, float gpa, Date birthDate,studentState state, int level, int completedHours,
                     String fname, String lname, String email, String password,  int deletedBy, int addedBy) {

        this.username = username;
        this.gpa = gpa;
        this.birthDate = birthDate;
        this.state = state;
        this.level = level;
        this.completedHours = completedHours;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.deletedBy = deletedBy;
        this.addedBy = addedBy;
    }
    public Student(int id, String username, String fname, String lname, String email, String password  ) {
        this.id = id;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;

    }
    public Student( String username, String fname, String lname, String email, String password  ) {
        this.id = id;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;

    }
    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public studentState getStudentState(){
        return state;
    }

    public void setStudentState(studentState state){
        this.state = state;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCompletedHours() {
        return completedHours;
    }

    public void setCompletedHours(int completedHours) {
        this.completedHours = completedHours;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(int deletedBy) {
        this.deletedBy = deletedBy;
    }

    public int getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(int addedBy) {
        this.addedBy = addedBy;
    }
}
