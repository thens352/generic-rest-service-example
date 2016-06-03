package com.thens.generic.service.imp;

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
public abstract class GenericServiceImp
        <Entity extends GenericEntity<DTO>, Key,
                DTO extends GenericDTO<Entity>> implements GenericService<Entity, Key, DTO> {

    private final static Logger logger = Logger.getLogger(GenericServiceImp.class);
    private GenericDao<Entity, Key> genericDao;

    public GenericServiceImp(GenericDao<Entity, Key> genericDao) {
        this.genericDao = genericDao;
    }

    public GenericServiceImp() {
    }

    @Transactional
    public DTO persist(DTO dto) {
        logger.debug(genericDao.getClass().getName() + " - persist service begin");
        try {
            dto = genericDao.persist(dto.toEntity()).toDTO();
            logger.debug(genericDao.getClass().getName() + " - persist service end");
            return dto;
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - persist service throws error : ", e);
            return null;
        }
    }

    @Transactional
    public DTO merge(DTO dto) {
        logger.debug(genericDao.getClass().getName() + " - merge service begin");
        try {
            dto = genericDao.merge(dto.toEntity()).toDTO();
            logger.debug(genericDao.getClass().getName() + " - merge service end");
            return dto;
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - merge service throws error : ", e);
            return null;
        }
    }

    @Transactional
    public boolean remove(Key key) {
        logger.debug(genericDao.getClass().getName() + " - remove service begin");
        try {
            boolean result = genericDao.remove(key);
            logger.debug(genericDao.getClass().getName() + " - remove service end");
            return result;
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - remove service throws error : ", e);
            return false;
        }
    }

    @Transactional(readOnly = true)
    public DTO find(Key key) {
        logger.debug(genericDao.getClass().getName() + " - find service begin");
        try {
            Entity entity = genericDao.find(key);
            if (entity == null) return null;

            DTO dto = entity.toDTO();
            logger.debug(genericDao.getClass().getName() + " - find service end");
            return dto;
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - find service throws error : ", e);
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<DTO> findAll() {
        logger.debug(genericDao.getClass().getName() + " - findAll service begin");
        List<DTO> dtos = new ArrayList<DTO>();
        List<Entity> entites;

        try {
            entites = genericDao.findAll();
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - findAll service throws error : ", e);
            return null;
        }
        if (entites != null)
            for (Entity entity : entites) {
                dtos.add(entity.toDTO());
            }

        logger.debug(genericDao.getClass().getName() + " - findAll service end");
        return dtos;
    }

    @Transactional(readOnly = true)
    public List<DTO> findByField(String field, Object value) {
        logger.debug(genericDao.getClass().getName() + " - findByField service begin with field : " + field + ", value : " + value);
        List<DTO> dtos = new ArrayList<DTO>();
        List<Entity> entites;

        try {
            entites = genericDao.findByField(field, value);
        } catch (Exception e) {
            logger.error(genericDao.getClass().getName() + " - findByField service throws error : ", e);
            return null;
        }

        if (entites != null)
            for (Entity entity : entites) {
                dtos.add(entity.toDTO());
            }

        logger.debug(genericDao.getClass().getName() + " - findByField service end");
        return dtos;
    }
}
