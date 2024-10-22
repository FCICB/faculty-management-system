package com.fcicb.model.service.impl;


import com.fcicb.domain.StudentCourse;
import com.fcicb.model.dao.impl.StudentCourseDao;
import com.fcicb.model.service.Service;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseService implements Service<StudentCourse> {
    StudentCourseDao studentCourseDao = new StudentCourseDao();
    public List<StudentCourse> reviewPassCourses(int id) {
        return studentCourseDao.reviewPassCourses(id);
    }

    @Override
    public boolean add(StudentCourse studentInfo) {
        if (studentInfo == null)
        {
            return false;
        }
        else
        {
            return studentCourseDao.add(studentInfo);
        }
    }

    @Override
    public StudentCourse getById(int id) {
        return null;
    }

    @Override
    public boolean update(StudentCourse studentInfo) {
        if(studentInfo == null)
            return false;
        else
            return studentCourseDao.update(studentInfo);
    }

    public int checkGrade(StudentCourse studentInfo) throws SQLException {
        return studentCourseDao.checkGrade(studentInfo);
    }

    public int getHour(StudentCourse studentInfo) throws SQLException {
        return studentCourseDao.getHour(studentInfo);
    }

    @Override
    public List<StudentCourse> getAll() {
        return null;
    }


    @Override
    public boolean add(List<StudentCourse> items) {
        return false;
    }

    @Override
    public boolean delete(StudentCourse item) {
        return false;
    }

    public boolean registerCourses(List<Integer> coursesID, int studentID){

        int totalHours;
        totalHours = studentCourseDao.calculateRegisteredHours((ArrayList<Integer>) coursesID);
        if(totalHours<=20){
            for (int id: coursesID){
                studentCourseDao.registerCourse(id,studentID);
            }
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<String> showAvailableCourses(int sID,int level){
        return studentCourseDao.showAvailableCourses(sID,level);
    }
    public int getLevel(Principal userPrincipal) {
        return studentCourseDao.getLevel(userPrincipal);
    }

    public int getId(Principal userPrincipal) {
        return studentCourseDao.getId(userPrincipal);
    }

    public int getCourseId(String s) {
        return studentCourseDao.getCourseId(s);
    }
}
