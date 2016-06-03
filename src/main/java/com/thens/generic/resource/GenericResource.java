package com.thens.generic.resource;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by Enes KURU on 12/5/2015.
 */
public interface GenericResource<Entity, Key, DTO> {
    Response persist(HttpHeaders request, DTO dto);

    Response merge(HttpHeaders request, Key key, DTO dto);

    Response remove(HttpHeaders request, Key key);

    Response find(HttpHeaders request, Key key);

    Response findAll(HttpHeaders request);

    Response findByField(HttpHeaders request, String field, String value);
}
