package com.fcicb.model.dao.impl;

import com.fcicb.domain.StudentCourse;
import com.fcicb.jdbc.DatabaseConnection;
import com.fcicb.model.dao.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentCourseDao implements Dao<StudentCourse> {

    DatabaseConnection instance = DatabaseConnection.getInstance();

    @Override
    public boolean add(StudentCourse studentInfo) {

        int result;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement insert = connection.prepareStatement(
                    "Insert INTO `studentCourse` (id, grade, courseId, studentId) VALUES (?, ?, ?, ?)");
            insert.setInt(1, studentInfo.getId());
            insert.setFloat(2, studentInfo.getGrade());
            insert.setString(3, studentInfo.getCourse().getCode());
            insert.setInt(4, studentInfo.getStudent().getId());

            result = insert.executeUpdate();

            if(checkGrade(studentInfo))
                return true;

            if ( result != 0 )
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkGrade(StudentCourse studentInfo) {

        int result = 0;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement checkGrade = connection.prepareStatement("SELECT grade from studentCourse where id = ?");
            checkGrade.setInt(1, studentInfo.getId());
            result = checkGrade.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(studentInfo.getGrade() >= 50 ) {
            retrieve(studentInfo);
        }

        return result != 0;
    }

    public int courseId(StudentCourse studentInfo) {
        int result = 0;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement courseId = connection.prepareStatement("SELECT courseId from studentCourse where id = ?");
            courseId.setInt(1, studentInfo.getId());
            result = courseId.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(StudentCourse studentInfo) {

        int result;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement conn = connection.prepareStatement("UPDATE `studentCourse` set grade = ? WHERE id = ? ");
            conn.setFloat(1, studentInfo.getGrade());
            conn.setInt(2, studentInfo.getId());

            result = conn.executeUpdate();

            if ( result != 0 )
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int retrieve(StudentCourse studentInfo) {

        int result = 0;
        int totalHour;
        int currentLevel;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement conn = connection.prepareStatement("Select hours from course where code = ?");
            conn.setString(1, studentInfo.getCourse().getCode());

            totalHour = result + studentInfo.getStudent().getCompletedHours();
            currentLevel = studentInfo.getStudent().getLevel();

            if(totalHour >= 34)
                currentLevel++;

            result = conn.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public StudentCourse getById(int id) {
        return null;
    }

    @Override
    public boolean add(List<StudentCourse> items) {
        return false;
    }

    @Override
    public List<StudentCourse> getAll() {
        return null;
    }

    @Override
    public boolean delete(StudentCourse item) {
        return false;
    }
}
