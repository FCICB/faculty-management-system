package com.fcicb.model.dao.impl;

import com.fcicb.domain.Course;
import com.fcicb.jdbc.DatabaseConnection;
import com.fcicb.model.dao.Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao implements Dao<Course> {
    DatabaseConnection instance = DatabaseConnection.getInstance();

    ResultSet rst = null;
    @Override
    public boolean add(Course item){
        int result ;
        Connection connection =null;
        try
        {
             connection = instance.getConnection();
            PreparedStatement insert = connection.prepareStatement("INSERT INTO `course` (code, name,hours,level,description,added_by)  VALUES (?,?,?,?,?,?)");
            insert.setString(1,item.getCode());
            insert.setString(2,item.getName());
            insert.setInt(3,item.getHours());
            insert.setInt(4,item.getLevel());
            insert.setString(5,item.getDescription());
            insert.setInt(6,item.getAddedBy());


            result=insert.executeUpdate();
            if(result != 0)
            {
                return true;
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
       }

        return false;
    }

    @Override
    public boolean add(List<Course> items) {
        return false;
    }

    @Override
    public Course getById(int id) {
        return null;
    }

    @Override
    public List<Course> getAll()
    {

       //test
        List<Course> courses =  new ArrayList<>();
        PreparedStatement list =null ;
         try
        {
            Connection connection = instance.getConnection();
            list = connection.prepareStatement("SELECT code, name,hours,level,description,added_by FROM course");
            rst = list.executeQuery();

            while(rst.next())
            {
                courses.add(new Course(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getInt(4),rst.getString(5),rst.getInt(6)));
            }
        }
        catch (SQLException e)
        {
        e.printStackTrace();
        }

        return courses;
    }

    @Override
    public boolean update(Course item) {
        return false;
    }

    @Override
    public boolean delete(Course id) {
        return false;
    }

}
