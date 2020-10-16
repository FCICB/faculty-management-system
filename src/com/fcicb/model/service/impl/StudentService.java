package com.fcicb.model.service.impl;

import com.fcicb.domain.Student;
import com.fcicb.model.dao.impl.StudentDao;
import com.fcicb.model.service.Service;

import java.security.Principal;
import java.util.List;

public class StudentService implements Service<Student> {

    StudentDao studentDao = new StudentDao();

    @Override
    public boolean add(Student item) {
        if (item == null)
            return false;
        else
            return studentDao.add(item);
    }

    @Override
    public boolean delete(Student item) {
        if (item == null)
            return false;
        else
            return studentDao.delete(item);
    }

    public boolean deactivate(Student item){
        if (item == null)
            return false;
        else
            return studentDao.deactivate(item);
    }

    public boolean reactivated(Student item){
        if (item == null)
            return false;
        else
            return studentDao.reactivate(item);
    }


    public  boolean updatePassword(Principal userName , String newpassword){

        return studentDao.updatePassword(userName,newpassword);
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

        return studentDao.getAll();

    }

    public boolean ActiveOrNot(Principal userName)
    {
        return studentDao.ActiveOrNot(userName);
    }

    public List<Student> getAllStudents() {

        return studentDao.getAllStudents();
    }

    public void generateTranscript(int studentID){
         studentDao.generateTranscript(studentID);
    }

    @Override
    public boolean update(Student item) {
        return false;
    }
    public int getId(Principal userPrincipal) {
        return  studentDao.getId(userPrincipal);
    }

    }
