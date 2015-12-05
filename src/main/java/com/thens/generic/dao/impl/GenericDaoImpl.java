package com.thens.generic.dao.impl;


import com.thens.generic.dao.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public abstract class GenericDaoImpl<Entity, Key extends Serializable> implements GenericDao<Entity, Key> {
    protected Class<Entity> entityType;

    @PersistenceContext
    private EntityManager entityManager;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityType = (Class) pt.getActualTypeArguments()[0];
    }

    public Entity persist(Entity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public Entity merge(Entity entity) {
        entityManager.merge(entity);
        return entity;
    }

    public boolean remove(Entity entity) {
        try {
            if (entityManager.contains(entity)) {
                entityManager.remove(entity);
                return true;
            } else {
                Entity newEntity = entityManager.merge(entity);
                entityManager.remove(newEntity);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Entity find(Key key) {
        return entityManager.find(entityType, key);
    }

    public List<Entity> findAll() {
        return entityManager.createQuery("from " + entityType.getName()).getResultList();
    }

    public List<Entity> findByField(String field, Object value) {
        return entityManager.createQuery("select e from " + entityType.getName() + " e where e." + field + " =:" + field)
                .setParameter(field, value).getResultList();
    }
}

