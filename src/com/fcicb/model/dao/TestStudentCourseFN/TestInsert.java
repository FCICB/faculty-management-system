package com.fcicb.model.dao.TestStudentCourseFN;

import com.fcicb.domain.Course;
import com.fcicb.domain.Student;
import com.fcicb.domain.StudentCourse;
import com.fcicb.jdbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestInsert {
    private static final DatabaseConnection instance = DatabaseConnection.getInstance();
//    DatabaseConnection instance = DatabaseConnection.getInstance();

    public static void main(String[] args) {
        StudentCourse studentInfo = new StudentCourse();
        Student std = new Student();
        Course course = new Course();
        int result;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement insert = connection.prepareStatement(
                    "Insert INTO `studentCourse` (id, grade, courseId, studentId) VALUES (?, ?, ?, ?)");
            studentInfo.setId(5);
            studentInfo.setGrade(60);
            course.setId(1);
            studentInfo.setCourse(course);
            std.setId(2);
            studentInfo.setStudent(std);
            insert.setInt(1, studentInfo.getId());
            insert.setFloat(2, studentInfo.getGrade());
            insert.setInt(3, studentInfo.getCourse().getId());
            insert.setInt(4, studentInfo.getStudent().getId());

            result = insert.executeUpdate();

            if (result != 0)
                System.out.println("Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}