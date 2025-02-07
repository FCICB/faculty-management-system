package com.fcicb.pdfGenerator;

import com.fcicb.domain.Student;
import com.fcicb.domain.StudentCourse;
import com.fcicb.jdbc.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class GetTranscriptInfoImp implements GetTranscriptInfo {

    private Student student;
    private ArrayList<StudentCourse> courses;
    private final Connection connection;
    private ResultSet rs;

    public GetTranscriptInfoImp() {
        DatabaseConnection instance = DatabaseConnection.getInstance();
        connection = instance.getConnection();
    }

    @Override
    public boolean checkStudent(int id){
        String sqlQuery1 = "SELECT studentId FROM studentCourse, student WHERE student.id = ? AND student.id = studentCourse.studentId";
        try (PreparedStatement pStmt = connection.prepareStatement(sqlQuery1)) {
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rs == null ? false : true;
    }

    @Override
    public void queryStudentInfo(int id) {
        String sqlQuery2 =  " SELECT fname, lname, GPA, level, completed_hours FROM student  WHERE id = ? ";
        student = new Student();

        try (PreparedStatement pStmt = connection.prepareStatement(sqlQuery2)) {
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
            if(rs.next()) {
                student.setFname(rs.getString(1));
                student.setLname(rs.getString(2));
                student.setGpa(rs.getFloat(3));
                student.setLevel(rs.getInt(4));
                student.setCompletedHours(rs.getInt(5));
                setStudent(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void queryCourseInfo(int id) {
        String sqlQuery3 = "  SELECT studentCourse.courseId, grade," +
                           "  (SELECT name FROM course as name  WHERE studentCourse.courseId = id ),\n" +
                           "  (SELECT hours FROM course as hours  WHERE studentCourse.courseId = id)\n" +
                           "  FROM studentCourse, student\n" +
                           "  WHERE student.id = ? AND student.id = studentCourse.studentId; ";

        courses = new ArrayList<>();

        try (PreparedStatement pStmt = connection.prepareStatement(sqlQuery3)) {
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                StudentCourse sc = new StudentCourse();
                sc.setGrade(rs.getFloat(2));
                sc.getCourse().setName(rs.getString(3));
                sc.getCourse().setHours(rs.getInt(4));
                courses.add(sc);
            }
            setCourses(courses);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
