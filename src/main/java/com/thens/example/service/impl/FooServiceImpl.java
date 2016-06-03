package com.thens.example.service.impl;

import com.thens.example.dao.FooDAO;
import com.thens.example.domain.Foo;
import com.thens.example.dto.FooDTO;
import com.thens.example.service.FooService;
import com.thens.generic.dao.GenericDao;
import com.thens.generic.service.imp.GenericServiceImp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Enes KURU on 3.6.2016.
 */
@Component
public class FooServiceImpl extends GenericServiceImp<Foo, Integer, FooDTO> implements FooService {

    private final static Logger logger = Logger.getLogger(FooServiceImpl.class);

    protected FooDAO fooDAO;

    public FooServiceImpl() {
    }

    @Autowired
    public FooServiceImpl(
            @Qualifier("fooDAOImpl") GenericDao<Foo, Integer> genericDao) {
        super(genericDao);
        this.fooDAO = (FooDAO) genericDao;
    }

}
