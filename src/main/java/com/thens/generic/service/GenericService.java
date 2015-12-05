package com.thens.generic.service;

import java.util.List;

public interface GenericService<E, K> {
    E persist(E entity);

    E merge(E entity);

    boolean remove(E entity);

    E find(K key);

    List<E> findAll();

    List<E> findByField(String field, Object value);
}
