package com.museum.controller;

import com.museum.model.Museum;
import com.museum.repository.MuseumRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/museums")
public class MuseumResource {

    @Inject
    Template museums;

    @Inject
    MuseumRepository museumRepository;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance list() {
        return museums.data("museums", museumRepository.listAll());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("name") String name,
                           @FormParam("description") String description,
                           @FormParam("latitude") Double latitude,
                           @FormParam("longitude") Double longitude,
                           @FormParam("landingPageUrl") String landingPageUrl) {
        museumRepository.persist(new Museum(name, description, latitude, longitude, landingPageUrl));
        return Response.seeOther(URI.create("/museums")).build();
    }

    @POST
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        museumRepository.deleteById(id);
        return Response.seeOther(URI.create("/museums")).build();
    }
}
