package com.fcicb.model.dao.impl;


import com.fcicb.domain.Student;
import com.fcicb.jdbc.DatabaseConnection;
import com.fcicb.model.dao.Dao;
import com.fcicb.utilities.JavaMailUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentDao implements Dao<Student> {

    DatabaseConnection instance = DatabaseConnection.getInstance();

    @Override
    public boolean add(Student item){

        int result ;

        try {
            Connection connection = instance.getConnection();
            PreparedStatement insert = connection.prepareStatement("INSERT INTO student  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                insert.setInt(1, item.getId());
                insert.setString(2, item.getUsername());
                insert.setFloat(3, item.getGpa());
                insert.setDate(4, (Date) item.getBirthDate());
                insert.setString(5, String.valueOf(item.getStudentState()));
                insert.setInt(6, item.getLevel());
                insert.setInt(7, item.getCompletedHours());
                insert.setString(8, item.getFname());
                insert.setString(9, item.getLname());
                insert.setString(10, item.getEmail());
                insert.setInt(11, item.getDeletedBy());


            result = insert.executeUpdate();

            if(result != 0)
            {
                JavaMailUtil.sendMail(item.getEmail(), item.getPassword());
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public boolean delete(Student student) {

        int result ;

        try  {
            Connection connection = instance.getConnection();
            PreparedStatement delete = connection.prepareStatement("DELETE FROM student  WHERE id = ? ");
            delete.setInt(1, student.getId());

            result = delete.executeUpdate();

            if ( result != 0 )
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deactivate(Student student){
        int result;

        try  {
            Connection connection = instance.getConnection();
            PreparedStatement deactivate = connection.prepareStatement("UPDATE  student  account_activated = 'Deactivated' WHERE id = ? ");
            deactivate.setInt(1, student.getId());

            result = deactivate.executeUpdate();

            if ( result != 0 )
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reactivate(Student student){
        int result;

        try  {
            Connection connection = instance.getConnection();
            PreparedStatement reactivated = connection.prepareStatement("UPDATE  student  account_activated = 'Activated' WHERE id = ? ");
            reactivated.setInt(1, student.getId());

            result = reactivated.executeUpdate();

            if ( result != 0 )
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public float calculateGPA(Student student){

        float totalGradePoints = 0;

        try  {
            Connection connection = instance.getConnection();
            PreparedStatement conn = connection.prepareStatement
                                                (" SELECT SUM(grade) AS 'totalGrades'" +
                                                        " FROM studentCourse " +
                                                        " WHERE id = ? ");
            conn.setInt(1, student.getId());

            totalGradePoints = conn.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalGradePoints / student.getCompletedHours() ;
    }


    @Override
    public boolean add(List<Student> items) {
        return false;
    }

    @Override
    public Student getById(int id) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public boolean update(Student item) {
        return false;
    }
}
