package com.fcicb.pdfGenerator;

import com.fcicb.domain.Student;
import com.fcicb.domain.StudentCourse;
import com.fcicb.jdbc.DatabaseConnection;

import java.sql.*;

import com.fcicb.domain.Course;

import java.util.ArrayList;

public class GetTranscriptInfoImp implements GetTranscriptInfo {

    private Student student;
    private ArrayList<StudentCourse> courses;
    private DatabaseConnection instance = DatabaseConnection.getInstance();
    private Connection connection;
    private ResultSet rs;

    public GetTranscriptInfoImp() {
        connection = instance.getConnection();
    }

    private void connectToDatabase() {
        Connection connection = instance.getConnection();
    }

    @Override
    public void queryStudentInfo(int id) {
        String sqlQuery1 =  " SELECT fname, lname, GPA, level, completed_hours FROM student;" +
                " WHERE id = ? ";
        student = new Student();

        try (PreparedStatement pStmt1 = connection.prepareStatement(sqlQuery1)) {
            pStmt1.setInt(1, id);
            rs = pStmt1.executeQuery();
            student.setFname(rs.getString(1));
            student.setLname(rs.getString(2));
            student.setGpa(rs.getFloat(3));
            student.setLevel(rs.getInt(4));
            student.setCompletedHours(rs.getInt(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void queryCourseInfo(int id) {
        String sqlQuery2 = " SELECT studentCourse.id, grade, " +
                " (SELECT name FROM course as name\n" +
                " WHERE studentCourse.id = id ),\n" +
                " (SELECT hours FROM course as hours\n" +
                " WHERE studentCourse.id = id)\n" +
                " FROM studentCourse, student\n" +
                " WHERE student.id = ? AND student.id = studentCourse.id; ";
        courses = new ArrayList<>();

        try (PreparedStatement pStmt2 = connection.prepareStatement(sqlQuery2)) {
            pStmt2.setInt(1, id);
            rs = pStmt2.executeQuery();
            while (rs.next()) {
                StudentCourse sc = new StudentCourse();
                sc.setGrade(rs.getFloat(2));
                sc.setCourseName(String.valueOf(rs.getFloat(3)));
                sc.setCourseCode(String.valueOf(rs.getFloat(4)));
                courses.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setCourses(courses);
    }

    @Override
    public Student getStudent() {
        return this.student;
    }

    @Override
    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public ArrayList<StudentCourse> getCourses() {
        return this.courses;
    }

    @Override
    public void setCourses(ArrayList<StudentCourse> courses) {
        this.courses = courses;
    }
}