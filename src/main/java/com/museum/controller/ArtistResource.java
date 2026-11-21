package com.museum.controller;

import com.museum.model.Artist;
import com.museum.repository.ArtistRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/artists")
public class ArtistResource {

    @Inject
    Template artists;

    @Inject
    ArtistRepository artistRepository;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance list() {
        return artists.data("artists", artistRepository.listAll());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("name") String name,
                           @FormParam("biography") String biography,
                           @FormParam("nationality") String nationality) {
        Artist artist = new Artist();
        artist.setName(name);
        artist.setBiography(biography);
        artist.setNationality(nationality);
        artistRepository.persist(artist);
        return Response.seeOther(URI.create("/artists")).build();
    }

    @POST
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        artistRepository.deleteById(id);
        return Response.seeOther(URI.create("/artists")).build();
    }
}