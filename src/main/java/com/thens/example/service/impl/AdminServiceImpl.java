package com.thens.example.service.impl;

import com.thens.example.dao.AdminDao;
import com.thens.example.model.Admin;
import com.thens.example.model.AdminDTO;
import com.thens.example.service.AdminService;
import com.thens.generic.dao.GenericDao;
import com.thens.generic.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceImpl extends GenericServiceImpl<Admin, Integer, AdminDTO>
        implements AdminService {

    private AdminDao adminDao;

    public AdminServiceImpl() {

    }

    @Autowired
    public AdminServiceImpl(
            @Qualifier("adminDaoImpl") GenericDao<Admin, Integer> genericDao) {
        super(genericDao);
        this.adminDao = (AdminDao) genericDao;
    }
}
