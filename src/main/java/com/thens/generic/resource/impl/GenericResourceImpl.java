package com.thens.generic.resource.impl;

import com.thens.generic.resource.GenericResource;
import com.thens.generic.service.GenericService;
import com.thens.generic.util.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.GenericEntity;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenericResourceImpl
        <Entity extends GenericEntity<DTO> & com.thens.generic.util.GenericEntity<DTO>, Key, DTO extends GenericDTO<Entity>> implements GenericResource<Entity, Key, DTO> {

    private GenericService<Entity, Key, DTO> genericService;

    public GenericResourceImpl(GenericService<Entity, Key, DTO> genericService) {
        this.genericService = genericService;
    }

    public GenericResourceImpl() {
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")

    public Response persist(@Context HttpHeaders request, @RequestBody DTO dto) {
        Cookie cookie = request.getCookies().get("UserToken");
        if (cookie == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.ok(genericService.persist(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response merge(@Context HttpHeaders request, @PathParam("id") Key key, DTO dto) {
        Cookie cookie = request.getCookies().get("UserToken");
        if (cookie == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.ok(genericService.merge(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@Context HttpHeaders request, @PathParam("id") Key key) {
        return Response.ok(genericService.remove(key)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@Context HttpHeaders request, @PathParam("id") Key key) {
        return Response.ok(genericService.find(key)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@Context HttpHeaders request) {
        return Response.ok(genericService.findAll()).build();
    }

    @GET
    @Path("/byField/{field}/{value}")
    @Produces("application/json")
    public Response findByField(@Context HttpHeaders request, @PathParam("field") String field, @PathParam("value") String value) {
        return Response.ok(genericService.findByField(field, value)).build();
    }
}
