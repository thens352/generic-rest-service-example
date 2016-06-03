package com.thens.example.resource;

import com.thens.example.dto.FooDTO;
import com.thens.example.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Enes KURU on 3.6.2016.
 */
@Component
@Path("/foo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FooResource {

    @Autowired
    protected FooService fooService;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response persist(@Context HttpHeaders request, FooDTO dto) {
        return Response.ok(fooService.persist(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response merge(@Context HttpHeaders request, @PathParam("id") Integer id, FooDTO dto) {
        return Response.ok(fooService.merge(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@Context HttpHeaders request, @PathParam("id") Integer id) {
        return Response.ok(fooService.remove(id)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@Context HttpHeaders request, @PathParam("id") Integer id) {
        return Response.ok(fooService.find(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@Context HttpHeaders request) {
        return Response.ok(fooService.findAll()).build();
    }

    @GET
    @Path("/byField/{field}/{value}")
    @Produces("application/json")
    public Response findByField(@Context HttpHeaders request, @PathParam("field") String field, @PathParam("value") String value) {
        return Response.ok(fooService.findByField(field, value)).build();
    }
}