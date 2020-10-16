package com.fcicb.model.dao.impl;
import com.fcicb.domain.Student;
import com.fcicb.jdbc.DatabaseConnection;
import com.fcicb.model.dao.Dao;
import com.fcicb.pdfGenerator.TranscriptGenerator;
import com.fcicb.utilities.JavaMailUtil;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.security.Principal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao<Student> {

    DatabaseConnection instance = DatabaseConnection.getInstance();


    @Override
    public boolean add(Student item)
    {

        int result ;

        try {
            Connection connection = instance.getConnection();
            PreparedStatement insert = connection.prepareStatement("INSERT INTO student (username,GPA,birth_date,account_activated,level,completed_hours,fname,lname,email,password ,added_by)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
            insert.setString(1, item.getUsername());
            insert.setFloat(2, item.getGpa());
            insert.setDate(3, (Date) item.getBirthDate());
            insert.setString(4, String.valueOf(item.getStudentState()));
            insert.setInt(5, item.getLevel());
            insert.setInt(6, item.getCompletedHours());
            insert.setString(7, item.getFname());
            insert.setString(8, item.getLname());
            insert.setString(9, item.getEmail());
            insert.setString(10, item.getPassword());
            insert.setInt(11, item.getAddedBy());

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
    public boolean delete(Student student)
    {

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

    public boolean deactivate(Student student)
    {
        int result;

        try  {
            Connection connection = instance.getConnection();
            PreparedStatement deactivate = connection.prepareStatement("UPDATE  student SET account_activated = 'Deactivated' WHERE id = ? ");
            deactivate.setInt(1, student.getId());

            result = deactivate.executeUpdate();

            if ( result != 0 )
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reactivate(Student student)
    {
        int result;

        try  {
            Connection connection = instance.getConnection();
            PreparedStatement reactivated = connection.prepareStatement("UPDATE  student SET account_activated = 'Activated' WHERE id = ? ");
            reactivated.setInt(1, student.getId());

            result = reactivated.executeUpdate();

            if ( result != 0 )
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean add(List<Student> items) {
        return false;
    }

    @Override
    public Student getById(int id) {
        return null;
    }

    // activation
    @Override
    public List<Student> getAll() {
        List<Student> students =  new ArrayList<>();
        PreparedStatement list =null ;
        ResultSet rst = null;
        try
        {
            Connection connection = instance.getConnection();
            list = connection.prepareStatement("SELECT id,username,fname,lname,email,password  FROM student where account_activated = 'Deactivated'");
            rst = list.executeQuery();

            while(rst.next())
            {
                students.add(new Student(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return students;

    }
    public List<Student> deactivateToLogin() {
        List<Student> students =  new ArrayList<>();
        PreparedStatement list =null ;
        ResultSet rst = null;
        try
        {
            Connection connection = instance.getConnection();
            list = connection.prepareStatement("SELECT id,username,fname,lname,email,password  FROM student where account_activated = 'Activated'");
            rst = list.executeQuery();

            while(rst.next())
            {
                students.add(new Student(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return students;

    }

    public boolean ActiveOrNot(Principal userName){
        List<Student> students=   deactivateToLogin();
        for (Student student : students)
        {

            if(student.getUsername().equals(userName.toString()) ==true)
            {

                return true;
            }
        }
        return false;
    }
    public List<Student> getAllStudents() {
        List<Student> students =  new ArrayList<>();
        PreparedStatement list =null ;
        ResultSet rst = null;
        try
        {
            Connection connection = instance.getConnection();
            list = connection.prepareStatement("SELECT id,username,fname,lname,email,password  FROM student");
            rst = list.executeQuery();

            while(rst.next())
            {
                students.add(new Student(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return students;
    }

    public void generateTranscript(int studentId) {
        TranscriptGenerator transcriptGenerator = null;
        boolean isStudentAvailable;
        try {
            transcriptGenerator = new TranscriptGenerator();
            isStudentAvailable = transcriptGenerator.isStudentInfoAvailable(studentId);
            if(isStudentAvailable){
                transcriptGenerator.generateTranscript(studentId);
            }else{
                System.out.println("student registered no courses yet !");
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean update(Student item) {
        return false;
    }

    public  boolean updatePassword(Principal userName , String newpassword){
        int result;

        try  {
            Connection connection = instance.getConnection();
            PreparedStatement reactivated = connection.prepareStatement("UPDATE  student SET password = ? WHERE username = ? ");
            reactivated.setString(1, newpassword);
            reactivated.setString(2, userName.toString());

            result = reactivated.executeUpdate();

            if ( result != 0 )
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

}


