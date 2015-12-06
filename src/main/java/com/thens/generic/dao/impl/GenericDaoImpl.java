package com.thens.generic.dao.impl;

import com.thens.generic.dao.GenericDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public abstract class GenericDaoImpl<Entity, Key extends Serializable> implements GenericDao<Entity, Key> {
    private final static Logger logger = Logger.getLogger(GenericDaoImpl.class);
    protected Class<Entity> entityType;

    @PersistenceContext
    private EntityManager entityManager;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityType = (Class) pt.getActualTypeArguments()[0];
    }

    /**
     * Save entity method
     *
     * @param entity
     * @return Entity
     */
    public Entity persist(Entity entity) {
        logger.debug(entityType.getName() + " - persist begin");
        try {
            entityManager.persist(entity);
            logger.debug(entityType.getName() + " - persist end");
            return entity;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - persist throws error : ", e);
            return null;
        }
    }

    /**
     * Update entity method
     *
     * @param entity
     * @return Entity
     */
    public Entity merge(Entity entity) {
        logger.debug(entityType.getName() + " - merge begin");
        try {
            entityManager.merge(entity);
            logger.debug(entityType.getName() + " - merge end");
            return entity;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - merge throws error : ", e);
            return null;
        }
    }

    /**
     * Remove entity method
     *
     * @param entity
     * @return boolean
     */
    public boolean remove(Entity entity) {
        logger.debug(entityType.getName() + " - remove begin");
        try {
            if (entityManager.contains(entity)) {
                entityManager.remove(entity);
            } else {
                Entity newEntity = entityManager.merge(entity);
                entityManager.remove(newEntity);
            }
            logger.debug(entityType.getName() + " - remove end");
            return true;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - remove throws error : ", e);
            return false;
        }
    }

    /**
     * Find entity method
     *
     * @param key
     * @return Entity
     */
    public Entity find(Key key) {
        Entity entity;
        logger.debug(entityType.getName() + " - find begin with key : " + key.toString());
        try {
            entity = entityManager.find(entityType, key);
            logger.debug(entityType.getName() + " - find end");
            return entity;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - find throws error : ", e);
            return null;
        }
    }

    /**
     * Find all entites method
     *
     * @return List<Entity>
     */
    public List<Entity> findAll() {
        List<Entity> entities;
        logger.debug(entityType.getName() + " - findAll begin");
        try {
            entities = entityManager.createQuery("from " + entityType.getName()).getResultList();
            logger.debug(entityType.getName() + " - find end");
            return entities;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - find throws error : ", e);
            return null;
        }
    }

    /**
     * Find entites by a field value
     *
     * @param field
     * @param value
     * @return List<Entity>
     */
    public List<Entity> findByField(String field, Object value) {
        List<Entity> entities;
        logger.debug(entityType.getName() + " - findByField begin with field : " + field + ", value : " + value);
        try {
            entities = entityManager.createQuery("select e from " + entityType.getName()
                    + " e where e." + field + " =:" + field).setParameter(field, value).getResultList();
            logger.debug(entityType.getName() + " - findByField end");
            return entities;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - findByField throws error : ", e);
            return null;
        }
    }
}

