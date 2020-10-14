package com.fcicb.domain;

public class StudentCourse {
    private int id;
    private float grade;
    private Student student;
    private Course course;


    public StudentCourse(String code ,String name, float grade)
    {
        course = new Course(code,name);
        this.grade = grade;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public int setStudent(Student student) {
        this.student = student;
        return 0;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
