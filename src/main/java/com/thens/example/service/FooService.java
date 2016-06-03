package com.thens.example.service;

import com.thens.example.domain.Foo;
import com.thens.example.dto.FooDTO;
import com.thens.generic.service.GenericService;

/**
 * Created by Enes KURU on 3.6.2016.
 */
public interface FooService extends GenericService<Foo, Integer, FooDTO> {
}
