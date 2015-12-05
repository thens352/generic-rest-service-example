package com.thens.generic.resource.impl;

import com.thens.generic.resource.GenericResource;
import com.thens.generic.service.GenericService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Enes KURU on 12/5/2015.
 */

@Component
public class GenericResourceImpl<E, K> implements GenericResource<E, K> {

    private GenericService<E, K> genericService;

    public GenericResourceImpl(GenericService<E, K> genericService) {
        this.genericService = genericService;
    }

    public GenericResourceImpl() {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public E persist(E entity) {
        return genericService.persist(entity);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public E merge(@PathParam("id") K key, E entity) {
        return genericService.merge(entity);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean remove(@PathParam("id") K key) {
        return genericService.remove(genericService.find(key));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public E find(@PathParam("id") K key) {
        return genericService.find(key);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<E> findAll() {
        return genericService.findAll();
    }

    @GET
    @Path("/byField/{field}/{value}")
    @Produces("application/json")
    public List<E> findByField(@PathParam("field") String field, @PathParam("value") String value) {
        return genericService.findByField(field, value);
    }
}
