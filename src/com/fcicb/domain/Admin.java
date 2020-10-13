package com.fcicb.domain;

public class Admin {

    private String userName;
    private String fname;
    private String lname;
    private String email;
    private String password;


    public Admin( String userName ,String fname , String lname ,String email, String password ){

        this.userName=userName;
        this.fname=fname;
        this.lname=lname;
        this.email=email;
        this.password=password;


    }
    public Admin(){

    }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserName() { return userName; }

    public void setFname(String fname) { this.fname = fname; }

    public String getFname() { return fname; }

    public void setLname(String lname) { this.lname = lname; }

    public String getLname() { return lname; }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return password; }

}