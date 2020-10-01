package com.fcicb.model.dao.impl;

import com.fcicb.domain.Course;
import com.fcicb.jdbc.DatabaseConnection;
import com.fcicb.model.dao.Dao;

import java.sql.*;
import java.util.List;

public class CourseDao implements Dao<Course> {
    DatabaseConnection instance = DatabaseConnection.getInstance();
    @Override
    public boolean add(Course item){
        int result ;
        try (Connection connection = instance.getConnection()) {
            PreparedStatement insert = connection.prepareStatement("INSERT INTO course  VALUES (?,?,?,?,?,?)");


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

        } catch (SQLException e) {
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
    public List<Course> getAll() {
        return null;
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
