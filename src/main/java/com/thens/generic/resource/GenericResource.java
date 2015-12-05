package com.thens.generic.resource;

import javax.ws.rs.core.Response;

/**
 * Created by Enes KURU on 12/5/2015.
 */
public interface GenericResource<Entity, Key, DTO> {
    Response persist(DTO dto);

    Response merge(Key key, DTO dto);

    Response remove(Key key);

    Response find(Key key);

    Response findAll();

    Response findByField(String field, String value);
}
