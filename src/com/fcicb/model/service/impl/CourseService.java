package com.fcicb.model.service.impl;

import com.fcicb.domain.Course;
import com.fcicb.model.dao.impl.CourseDao;
import com.fcicb.model.service.Service;

import java.util.List;

public class CourseService implements Service<Course> {
    CourseDao courseDao = new CourseDao();
    @Override
    public boolean add(Course item) {
        if (item == null)
        {
            return false;
        }
        else
        {
            return courseDao.add(item);
        }
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
    public boolean delete(Course item) {
        return false;
    }
}
