package com.thens.generic.service.impl;

import com.thens.generic.dao.GenericDao;
import com.thens.generic.service.GenericService;
import com.thens.generic.util.GenericDTO;
import com.thens.generic.util.GenericEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class GenericServiceImpl
        <Entity extends GenericEntity<DTO>, Key,
                DTO extends GenericDTO<Entity>> implements GenericService<Entity, Key, DTO> {

    private final static Logger logger = Logger.getLogger(GenericServiceImpl.class);
    private GenericDao<Entity, Key> genericDao;

    public GenericServiceImpl(GenericDao<Entity, Key> genericDao) {
        this.genericDao = genericDao;
    }

    public GenericServiceImpl() {
    }

    @Transactional
    public DTO persist(DTO dto) {
        logger.debug(genericDao.getClass().getName() + " - persist begin");
        try {
            dto = genericDao.persist(dto.toEntity()).toDTO();
            logger.debug(genericDao.getClass().getName() + " - persist end");
            return dto;
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - persist throws error : ", e);
            return null;
        }
    }

    @Transactional
    public DTO merge(DTO dto) {
        logger.debug(genericDao.getClass().getName() + " - merge begin");
        try {
            dto = genericDao.merge(dto.toEntity()).toDTO();
            logger.debug(genericDao.getClass().getName() + " - merge end");
            return dto;
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - merge throws error : ", e);
            return null;
        }
    }

    @Transactional
    public boolean remove(DTO dto) {
        logger.debug(genericDao.getClass().getName() + " - remove begin");
        try {
            boolean result = genericDao.remove(dto.toEntity());
            logger.debug(genericDao.getClass().getName() + " - remove end");
            return result;
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - remove throws error : ", e);
            return false;
        }
    }

    public DTO find(Key key) {
        logger.debug(genericDao.getClass().getName() + " - find begin");
        try {
            DTO dto = genericDao.find(key).toDTO();
            logger.debug(genericDao.getClass().getName() + " - find end");
            return dto;
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - find throws error : ", e);
            return null;
        }
    }

    public List<DTO> findAll() {
        logger.debug(genericDao.getClass().getName() + " - findAll begin");
        List<DTO> dtos = new ArrayList<DTO>();
        List<Entity> entites;

        try {
            entites = genericDao.findAll();
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - findAll throws error : ", e);
            return null;
        }
        for (Entity entity : entites) {
            dtos.add(entity.toDTO());
        }

        logger.debug(genericDao.getClass().getName() + " - findAll end");
        return dtos;
    }

    public List<DTO> findByField(String field, Object value) {
        logger.debug(genericDao.getClass().getName() + " - findByField begin with field : " + field + ", value : " + value);
        List<DTO> dtos = new ArrayList<DTO>();
        List<Entity> entites;

        try {
            entites = genericDao.findByField(field, value);
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - findByField throws error : ", e);
            return null;
        }
        for (Entity entity : entites) {
            dtos.add(entity.toDTO());
        }

        logger.debug(genericDao.getClass().getName() + " - findByField end");
        return dtos;
    }
}
