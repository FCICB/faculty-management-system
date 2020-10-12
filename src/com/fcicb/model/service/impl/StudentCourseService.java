package com.fcicb.model.service.impl;

import com.fcicb.domain.Course;
import com.fcicb.domain.Student;
import com.fcicb.domain.StudentCourse;
import com.fcicb.model.dao.impl.StudentCourseDao;
import com.fcicb.model.service.Service;

import java.sql.SQLException;
import java.util.List;

public class StudentCourseService implements Service<StudentCourse> {
    StudentCourseDao studentCourseDao = new StudentCourseDao();

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

    public boolean checkGrade(StudentCourse studentInfo) throws SQLException {
        if (studentInfo == null)
            return false;
        else
            return studentCourseDao.checkGrade(studentInfo);
    }

    public int retrieve(StudentCourse studentInfo) throws SQLException {
       return studentCourseDao.retrieve(studentInfo);
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

}
