package com.thens.generic.service.impl;

import com.thens.generic.dao.GenericDao;
import com.thens.generic.service.GenericService;
import com.thens.generic.util.GenericDTO;
import com.thens.generic.util.GenericEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class GenericServiceImpl
        <Entity extends GenericEntity<DTO>, Key, 
                DTO extends GenericDTO<Entity>> implements GenericService<Entity, Key, DTO> {

    private GenericDao<Entity, Key> genericDao;

    public GenericServiceImpl(GenericDao<Entity, Key> genericDao) {
        this.genericDao = genericDao;
    }

    public GenericServiceImpl() {
    }

    @Transactional
    public DTO persist(DTO dto) {
        return genericDao.persist(dto.toEntity()).toDTO();
    }

    @Transactional
    public DTO merge(DTO dto) {
        return genericDao.merge(dto.toEntity()).toDTO();
    }

    @Transactional
    public boolean remove(DTO dto) {
        return genericDao.remove(dto.toEntity());
    }

    public DTO find(Key key) {
        return genericDao.find(key).toDTO();
    }

    public List<DTO> findAll() {
        List<Entity> entites = genericDao.findAll();
        List<DTO> dtos = new ArrayList<DTO>();
        for (Entity entity : entites)
            dtos.add(entity.toDTO());

        return dtos;
    }

    public List<DTO> findByField(String field, Object value) {
        List<Entity> entites = genericDao.findByField(field, value);
        List<DTO> dtos = new ArrayList<DTO>();
        for (Entity entity : entites)
            dtos.add(entity.toDTO());

        return dtos;
    }
}
