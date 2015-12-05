package com.thens.generic.service.impl;

import com.thens.generic.dao.GenericDao;
import com.thens.generic.service.GenericService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K> {

    private GenericDao<E, K> genericDao;

    public GenericServiceImpl(GenericDao<E, K> genericDao) {
        this.genericDao = genericDao;
    }

    public GenericServiceImpl() {
    }

    @Transactional
    public E persist(E entity) {
        return genericDao.persist(entity);
    }

    @Transactional
    public E merge(E entity) {
        return genericDao.merge(entity);
    }

    @Transactional
    public boolean remove(E entity) {
        return genericDao.remove(entity);
    }

    public E find(K key) {
        return genericDao.find(key);
    }

    public List<E> findAll() {
        return genericDao.findAll();
    }

    public List<E> findByField(String field, Object value){
        return genericDao.findByField(field,value);
    }
}
