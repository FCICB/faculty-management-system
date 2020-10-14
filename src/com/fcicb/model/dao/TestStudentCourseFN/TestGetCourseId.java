package com.fcicb.model.dao.TestStudentCourseFN;

import com.fcicb.domain.Student;
import com.fcicb.domain.StudentCourse;
import com.fcicb.jdbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestGetCourseId {
    private static final DatabaseConnection instance = DatabaseConnection.getInstance();

    public static void main(String[] args) {
        StudentCourse studentInfo = new StudentCourse();
        Student std = new Student();
        ResultSet rst;
        int id = 0;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement courseId = connection.prepareStatement("SELECT courseId from studentCourse where id = ?");

            studentInfo.setId(5);
            studentInfo.setStudent(std);
            courseId.setInt(1, studentInfo.getId());
            rst = courseId.executeQuery();

            while (rst.next()) {
                id = rst.getInt("courseId");
            }
            System.out.println(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
