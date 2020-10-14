package com.fcicb.model.dao.TestStudentCourseFN;

import com.fcicb.domain.Course;
import com.fcicb.domain.Student;
import com.fcicb.domain.StudentCourse;
import com.fcicb.jdbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestGrade {
    private static final DatabaseConnection instance = DatabaseConnection.getInstance();

    public static void main(String[] args) {
        StudentCourse studentInfo = new StudentCourse();
        Student std = new Student();
        Course course = new Course();
        ResultSet rst, rst2, rst3;
        int totalHour = 0, hour = 0, currentLevel = 0, grade;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement conn = connection.prepareStatement("Select hours from course where id = ?");
            PreparedStatement conn2 = connection.prepareStatement("Select completed_hours, level from student where id = ?");
            PreparedStatement checkGrade = connection.prepareStatement("SELECT grade from studentCourse where id = ?");
            course.setId(1);
            studentInfo.setCourse(course);
            std.setId(1);
            studentInfo.setStudent(std);
            conn.setInt(1,studentInfo.getCourse().getId());
            checkGrade.setInt(1, studentInfo.getStudent().getId());
            conn2.setInt(1, studentInfo.getStudent().getId());
            rst = conn.executeQuery();
            rst2 = conn2.executeQuery();
            rst3 = checkGrade.executeQuery();

            while(rst.next()) {
                hour = rst.getInt("hours");
            }
            System.out.println("course hour: " + hour);

            while (rst2.next()) {
                totalHour = rst2.getInt("completed_hours");
                currentLevel = rst2.getInt("level");
            }
            System.out.println("level: " + currentLevel);
            totalHour += hour;
            System.out.println("total hour: " + totalHour);

            while (rst3.next()) {
                grade = rst3.getInt("grade");
                if(grade >= 50) {
                    System.out.println("my grade: " + grade);
                }
            }

            if(totalHour >= 34) {
                currentLevel++;
            }
            System.out.println("current level after passed: " + currentLevel);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
