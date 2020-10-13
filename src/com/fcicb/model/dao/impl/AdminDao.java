package com.fcicb.model.dao.impl;
import com.fcicb.domain.Admin;
import com.fcicb.jdbc.DatabaseConnection;
import com.fcicb.model.dao.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

public class AdminDao implements Dao<Admin> {


    DatabaseConnection AdminConObj = DatabaseConnection.getInstance();
    @Override
    public  boolean add(Admin item){

        try {
            Connection conn = AdminConObj.getConnection();
            PreparedStatement register = conn.prepareStatement("INSERT INTO admin(email,fname,lname,password,username) VALUES (?,?,?,?,?)");

            register.setString(1,item.getEmail());
            register.setString(2,item.getFname());
            register.setString(3,item.getLname());
            item.setPassword(encode(item.getPassword()));
            register.setString(4,item.getPassword());
            register.setString(5,item.getUserName());
            int rst = register.executeUpdate();


            if(rst!= 0)
            {
                return true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String encode (String str){
        byte[] encoded = null;
        try {
            Base64.Encoder encoder = Base64.getEncoder(); //create instance of base64 encoder
            encoded = encoder.encode(str.getBytes()); // encoding

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return new String(encoded);
    }



    @Override
    public boolean add(List<Admin> items){return false;}

    @Override
    public Admin getById(int id) {
        return null;
    }

    @Override
    public List<Admin> getAll() { return null; }

    @Override
    public boolean update(Admin item) {
        return false;
    }

    @Override
    public boolean delete(Admin id) {
        return false;
    }



}