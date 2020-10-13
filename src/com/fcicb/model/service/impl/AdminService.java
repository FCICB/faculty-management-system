package com.fcicb.model.service.impl;

import com.fcicb.domain.Admin;
import com.fcicb.model.dao.impl.AdminDao;
import com.fcicb.model.service.Service;

import java.util.List;

public class AdminService implements Service<Admin> {
    AdminDao adminDao = new AdminDao();
    @Override
    public boolean add(Admin item) {
        if (item == null)
        {
            return false;
        }
        else
        {
            return adminDao.add(item);
        }
    }

    @Override
    public boolean add(List<Admin> items) { return false; }

    @Override
    public Admin getById(int id) { return null; }

    @Override
    public List<Admin> getAll() { return null; }

    @Override
    public boolean update(Admin item) { return false; }

    @Override
    public boolean delete(Admin item) { return false; }
}

