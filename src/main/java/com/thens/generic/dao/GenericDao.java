package com.thens.generic.dao;

import java.util.List;

public interface GenericDao<Entity, Key> {
    Entity persist(Entity entity);

    Entity merge(Entity entity);

    boolean remove(Key key);

    Entity find(Key key);

    List<Entity> findAll();

    List<Entity> findByField(String field, Object value);

    Entity executeNamedQueryForSingleResult(String queryName, List<Object> parameterList);

    List<Entity> executeNamedQueryForResultList(String queryName, List<Object> parameterList);
}
