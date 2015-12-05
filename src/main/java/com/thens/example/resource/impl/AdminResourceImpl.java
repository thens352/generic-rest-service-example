package com.thens.example.resource.impl;

import com.thens.example.model.Admin;
import com.thens.example.model.AdminDTO;
import com.thens.example.resource.AdminResource;
import com.thens.example.service.AdminService;
import com.thens.generic.resource.impl.GenericResourceImpl;
import com.thens.generic.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

/**
 * Created by Enes KURU on 12/5/2015.
 */
@Component
@Path("/admin")
public class AdminResourceImpl extends GenericResourceImpl<Admin, Integer, AdminDTO> implements AdminResource {
    private AdminService adminService;

    public AdminResourceImpl() {
    }

    @Autowired
    public AdminResourceImpl(
            @Qualifier("adminServiceImpl") GenericServiceImpl<Admin, Integer, AdminDTO> genericServiceImpl) {
        super(genericServiceImpl);
        this.adminService = (AdminService) genericServiceImpl;
    }
}
