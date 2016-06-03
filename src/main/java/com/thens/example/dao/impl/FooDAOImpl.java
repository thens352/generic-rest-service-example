package com.thens.example.dao.impl;

import com.thens.example.dao.FooDAO;
import com.thens.example.domain.Foo;
import com.thens.generic.dao.imp.GenericDaoImp;
import org.springframework.stereotype.Repository;

/**
 * Created by Enes KURU on 3.6.2016.
 */
@Repository
public class FooDAOImpl extends GenericDaoImp<Foo, Integer> implements FooDAO {

}
