package com.fcicb.model.dao.TestStudentCourseFN;

import com.fcicb.domain.Student;
import com.fcicb.domain.StudentCourse;
import com.fcicb.jdbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUpdate {
    private static final DatabaseConnection instance = DatabaseConnection.getInstance();

    public static void main(String[] args) {
        StudentCourse studentInfo = new StudentCourse();
        Student std = new Student();

        try {
            Connection connection = instance.getConnection();
            PreparedStatement conn = connection.prepareStatement("UPDATE `studentCourse` set grade = ? WHERE id = ?");

            studentInfo.setGrade(85);
            studentInfo.setId(2);
            studentInfo.setStudent(std);

            conn.setFloat(1, studentInfo.getGrade());
            conn.setInt(2, studentInfo.getId());

            conn.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
