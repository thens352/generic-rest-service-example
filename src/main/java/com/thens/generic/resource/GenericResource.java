package com.thens.generic.resource;

import java.util.List;

/**
 * Created by Enes KURU on 12/5/2015.
 */
public interface GenericResource<E, K> {
    E persist(E entity);

    E merge(K key, E entity);

    boolean remove(K key);

    E find(K key);

    List<E> findAll();

    List<E> findByField(String field, String value);
}
