package com.museum.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/")
public class RootResource {

    @GET
    public Response home() {
        return Response.seeOther(URI.create("/museums")).build();
    }

    @GET
    @Path("/museos")
    public Response oldMuseos() {
        return Response.seeOther(URI.create("/museums")).build();
    }

    @GET
    @Path("/salas")
    public Response oldSalas() {
        return Response.seeOther(URI.create("/rooms")).build();
    }

    @GET
    @Path("/visitas")
    public Response oldVisitas() {
        return Response.seeOther(URI.create("/tours")).build();
    }
}
