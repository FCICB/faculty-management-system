package com.fcicb.model.dao.impl;

import com.fcicb.domain.Course;
import com.fcicb.domain.StudentCourse;
import com.fcicb.jdbc.DatabaseConnection;
import com.fcicb.model.dao.Dao;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao implements Dao<StudentCourse> {

    DatabaseConnection instance = DatabaseConnection.getInstance();
    public List<StudentCourse> reviewPassCourses(int id)
    {
        ResultSet rst;
        List<StudentCourse> list = new ArrayList<>();
        Connection connection;
        PreparedStatement getCourses;
        try {
            connection = instance.getConnection();
            getCourses= connection.prepareStatement("SELECT code, name, grade FROM studentCourse inner join course on\n" +
                    "studentCourse.courseId = course.id\n" +
                    "inner JOIN student on\n" +
                    "studentCourse.studentId = student.id\n" +
                    "where grade >=50 and studentId = ?;");
            getCourses.setInt(1,id);
            rst = getCourses.executeQuery();
            while (rst.next())
            {

                list.add(new StudentCourse(rst.getString(1),rst.getString(2),rst.getInt(3)));

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }

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

    public boolean registerCourse(int cID, int sID) {
        try {
            ResultSet rst;
            Connection connection = instance.getConnection();
            PreparedStatement gradeQuery = connection.prepareStatement
                    ("SELECT grade FROM fms.studentCourse WHERE studentId = ? AND courseId = ?");
            gradeQuery.setInt(1, sID);
            gradeQuery.setInt(2, cID);
            PreparedStatement courseQuery = connection.prepareStatement
                    ("UPDATE studentCourse SET grade=0 WHERE studentId = ? AND courseId = ?");
            courseQuery.setInt(1, sID);
            courseQuery.setInt(2, cID);
            PreparedStatement firstRegistration = connection.prepareStatement
                    ("INSERT INTO studentCourse (courseId,studentId)  VALUES (?,?)");
            firstRegistration.setInt(1, cID);
            firstRegistration.setInt(2, sID);
            rst = gradeQuery.executeQuery();
            if (rst.next()) {
                courseQuery.executeUpdate();
                return true;
            } else {
                int result = firstRegistration.executeUpdate();
                return (result == 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int calculateRegisteredHours(ArrayList<Integer> courses){
        int totalHours=0;
        try{
            Connection connection = instance.getConnection();
            PreparedStatement courseQuery = connection.prepareStatement("SELECT hours FROM course WHERE id = ?");
            for (int id: courses){
                courseQuery.setInt(1, id);
                ResultSet r =  courseQuery.executeQuery();
                if(r.next()){
                    int courseHours = r.getInt("hours");
                    totalHours+=courseHours;}
            }
            return totalHours;
        }catch (SQLException e){
            e.printStackTrace();
            return totalHours;
        }
    }

    public ArrayList<String> showAvailableCourses(int level){
        ArrayList<String> availableCourses = new ArrayList<String>();
        try{
            Connection connection = instance.getConnection();
            PreparedStatement courseQuery = connection.prepareStatement("SELECT name FROM course WHERE level = ?");
            courseQuery.setInt(1, level);
            ResultSet r =  courseQuery.executeQuery();
            while(r.next()) {
                availableCourses.add(r.getString("name"));
            }
            return availableCourses;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public int getId(Principal userPrincipal) {

        ResultSet rst;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement yourID = connection.prepareStatement("SELECT id from student  where username = ?");
            yourID.setString(1, userPrincipal.toString());
            rst = yourID.executeQuery();

            if(rst.next()){
                int id = rst.getInt(1);
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  0;
    }
    public int getLevel(Principal userPrincipal) {

        ResultSet rst;
        try {
            Connection connection = instance.getConnection();
            PreparedStatement yourLevel = connection.prepareStatement("SELECT level from student  where username = ?");
            yourLevel.setString(1, userPrincipal.toString());
            rst = yourLevel.executeQuery();

            if(rst.next()){

                int level = rst.getInt(1);
                System.out.println(level);
                return level;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  0;
    }

    public int getCourseId(String courseName) {
        ResultSet rst;
        try {
            Connection connection = instance.getConnection();
            //  PreparedStatement courseId = connection.prepareStatement("SELECT courseId FROM fms.studentCourse inner join  course ON fms.studentCourse.courseId = fms.course.id  where name =?");
            PreparedStatement courseId = connection.prepareStatement("SELECT id FROM  course  where name =?");
            courseId.setString(1, courseName);
            rst = courseId.executeQuery();

            if(rst.next()){

                int id = rst.getInt(1);
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  0;

    }
}

