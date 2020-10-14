package com.fcicb.model.dao.impl;

import com.fcicb.domain.StudentCourse;
import com.fcicb.jdbc.DatabaseConnection;
import com.fcicb.model.dao.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

            if ( result != 0 )
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int checkGrade(StudentCourse studentInfo) {

        int grade = 0;
        ResultSet rst;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement checkGrade = connection.prepareStatement("SELECT grade from studentCourse where id = ?");
            checkGrade.setInt(1, studentInfo.getStudent().getId());
            rst = checkGrade.executeQuery();

            while (rst.next()) {
                grade = rst.getInt("grade");
                if(grade >= 50) {
                    return grade;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grade;
    }

    public int getCourseId(StudentCourse studentInfo) {
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
    public int getHour(StudentCourse studentInfo) {

        ResultSet rst;
        ResultSet rst2;
        int totalHour = 0;
        int hour = 0;
        int currentLevel = 0;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement conn = connection.prepareStatement("Select hours from course where id = 1");
            PreparedStatement conn2 = connection.prepareStatement("Select completed_hours, level from student where id = ?");

            conn2.setInt(1, studentInfo.getStudent().getId());
            rst = conn.executeQuery();
            rst2 = conn2.executeQuery();

            while(rst.next()) {
                hour = rst.getInt("hours");
            }
            while (rst2.next()) {
                totalHour = rst2.getInt("completed_hours");
                currentLevel = rst2.getInt("level");
            }
            totalHour += hour;

            if(totalHour >= 34) {
                currentLevel++;
                return currentLevel;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalHour;
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
