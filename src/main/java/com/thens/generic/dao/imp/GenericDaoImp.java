package com.thens.generic.dao.imp;

import com.thens.generic.dao.GenericDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public abstract class GenericDaoImp<Entity, Key extends Serializable> implements GenericDao<Entity, Key> {
    private final static Logger logger = Logger.getLogger(GenericDaoImp.class);
    protected Class<Entity> entityType;

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericDaoImp() {
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
        logger.debug(entityType.getName() + " - persist dao begin");
        try {
            entityManager.persist(entity);
            logger.debug(entityType.getName() + " - persist dao end");
            return entity;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - persist dao throws error : ", e);
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
        logger.debug(entityType.getName() + " - merge dao begin");
        try {
            entityManager.merge(entity);
            logger.debug(entityType.getName() + " - merge dao end");
            return entity;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - merge dao throws error : ", e);
            return null;
        }
    }

    /**
     * Remove entity method
     *
     * @param key
     * @return boolean
     */
    public boolean remove(Key key) {
        logger.debug(entityType.getName() + " - remove dao begin");
        try {
            Entity entity = find(key);
            if (entityManager.contains(entity)) {
                entityManager.remove(entity);
            } else {
                Entity newEntity = entityManager.merge(entity);
                entityManager.remove(newEntity);
            }
            logger.debug(entityType.getName() + " - remove dao end");
            return true;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - remove dao throws error : ", e);
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
        logger.debug(entityType.getName() + " - find dao begin with key : " + key.toString());
        try {
            entity = entityManager.find(entityType, key);
            logger.debug(entityType.getName() + " - find dao end");
            return entity;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - find dao throws error : ", e);
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
        logger.debug(entityType.getName() + " - findAll dao begin");
        try {
            entities = entityManager.createQuery("from " + entityType.getName(), entityType).getResultList();
            logger.debug(entityType.getName() + " - find dao end");
            return entities;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - find dao throws error : ", e);
            return null;
        }
    }

    public Entity executeNamedQueryForSingleResult(String queryName, List<Object> parameterList) {
        Entity entity;
        logger.debug(entityType.getName() + "- executeNamedQueryForSingleResult dao begin");
        try {
            Query query = entityManager.createNamedQuery(queryName, entityType);

            if (parameterList != null)
                for (Object obj : parameterList) {
                    query.setParameter(parameterList.indexOf(obj) + 1, obj);
                }

            entity = (Entity) query.getSingleResult();
            logger.debug(entityType.getName() + " - executeNamedQueryForSingleResult dao end");
            return entity;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - executeNamedQueryForSingleResult dao throws error : ", e);
            return null;
        }
    }

    public List<Entity> executeNamedQueryForResultList(String queryName, List<Object> parameterList) {
        List<Entity> entities;
        logger.debug(entityType.getName() + "- executeNamedQueryForResultList dao begin");
        try {
            Query query = entityManager.createNamedQuery(queryName, entityType);

            if (parameterList != null)
                for (Object obj : parameterList) {
                    query.setParameter(parameterList.indexOf(obj) + 1, obj);
                }

            entities = query.getResultList();
            logger.debug(entityType.getName() + " - executeNamedQueryForResultList dao end");
            return entities;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - executeNamedQueryForResultList dao throws error : ", e);
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
    //TODO change github generic rest service repo
    public List<Entity> findByField(String field, Object value) {
        List entities;
        logger.debug(entityType.getName() + " - findByField begin with field : " + field + ", value : " + value);
        try {
            if (value.toString().matches("[-+]?\\d*\\.?\\d+")) {
                entities = entityManager.createQuery("select e from " + entityType.getName()
                        + " e where e." + field + " =?1").setParameter(1, Integer.parseInt(value.toString())).getResultList();
            } else {
                entities = entityManager.createQuery("select e from " + entityType.getName()
                        + " e where e." + field + " =?1").setParameter(1, (String) value).getResultList();
            }
            logger.debug(entityType.getName() + " - findByField end");
            return entities;
        } catch (Exception e) {
            logger.error(entityType.getName() + " - findByField throws error : ", e);
            return null;
        }
    }
}

