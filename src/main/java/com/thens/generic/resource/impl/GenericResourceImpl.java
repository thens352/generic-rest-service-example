package com.thens.generic.resource.impl;

import com.thens.generic.resource.GenericResource;
import com.thens.generic.service.GenericService;
import com.thens.generic.util.GenericDTO;
import com.thens.generic.util.GenericEntity;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Enes KURU on 12/5/2015.
 */

@Component
public class GenericResourceImpl
        <Entity extends GenericEntity<DTO>, Key, DTO extends GenericDTO<Entity>> implements GenericResource<Entity, Key, DTO> {

    private GenericService<Entity, Key, DTO> genericService;

    public GenericResourceImpl(GenericService<Entity, Key, DTO> genericService) {
        this.genericService = genericService;
    }

    public GenericResourceImpl() {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persist(DTO dto) {
        return Response.ok(genericService.persist(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response merge(@PathParam("id") Key key, DTO dto) {
        return Response.ok(genericService.merge(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response remove(@PathParam("id") Key key) {
        return Response.ok(genericService.remove(genericService.find(key))).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Key key) {
        return Response.ok(genericService.find(key)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(genericService.findAll()).build();
    }

    @GET
    @Path("/byField/{field}/{value}")
    @Produces("application/json")
    public Response findByField(@PathParam("field") String field, @PathParam("value") String value) {
        return Response.ok(genericService.findByField(field, value)).build();
    }
}
