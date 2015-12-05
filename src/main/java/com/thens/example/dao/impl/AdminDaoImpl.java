package com.thens.example.dao.impl;

import com.thens.example.dao.AdminDao;
import com.thens.example.model.Admin;
import com.thens.generic.dao.impl.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl extends GenericDaoImpl<Admin, Integer> implements AdminDao {

}
