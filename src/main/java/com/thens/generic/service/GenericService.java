package com.thens.generic.service;

import com.thens.generic.util.GenericDTO;
import com.thens.generic.util.GenericEntity;

import java.util.List;

public interface GenericService<Entity extends GenericEntity<DTO>, Key, DTO extends GenericDTO<Entity>> {
    DTO persist(DTO entity);

    DTO merge(DTO entity);

    boolean remove(DTO entity);

    DTO find(Key key);

    List<DTO> findAll();

    List<DTO> findByField(String field, Object value);
}
