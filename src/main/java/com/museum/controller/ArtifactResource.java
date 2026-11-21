package com.museum.controller;

import com.museum.model.Artifact;
import com.museum.repository.ArtifactRepository;
import com.museum.repository.ArtistRepository;
import com.museum.repository.CategoryRepository;
import com.museum.repository.ExhibitionRoomRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.time.LocalDateTime;

@Path("/artifacts")
public class ArtifactResource {

    @Inject
    Template artifacts;

    @Inject
    ArtifactRepository artifactRepository;

    @Inject
    ArtistRepository artistRepository;

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    ExhibitionRoomRepository roomRepository;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance list() {
        return artifacts
                .data("artifacts", artifactRepository.listAll())
                .data("artists", artistRepository.listAll())
                .data("categories", categoryRepository.listAll())
                .data("rooms", roomRepository.listAll());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("name") String name,
                           @FormParam("description") String description,
                           @FormParam("imageUrl") String imageUrl,
                           @FormParam("createdAt") String createdAt,
                           @FormParam("acquiredAt") String acquiredAt,
                           @FormParam("conditionPercentage") Integer conditionPercentage,
                           @FormParam("categoryId") Long categoryId,
                           @FormParam("artistId") Long artistId,
                           @FormParam("exhibitionRoomId") Long exhibitionRoomId) {
        Artifact artifact = new Artifact();
        artifact.setName(name);
        artifact.setDescription(description);
        artifact.setImageUrl(imageUrl);
        artifact.setConservationPercentage(conditionPercentage);

        if (createdAt != null && !createdAt.isBlank()) {
            artifact.setCreatedAt(LocalDateTime.parse(createdAt));
        }
        if (acquiredAt != null && !acquiredAt.isBlank()) {
            artifact.setAcquiredAt(LocalDateTime.parse(acquiredAt));
        }
        if (categoryId != null) {
            artifact.setCategory(categoryRepository.findById(categoryId));
        }
        if (artistId != null) {
            artifact.setArtist(artistRepository.findById(artistId));
        }
        if (exhibitionRoomId != null) {
            artifact.setCurrentRoom(roomRepository.findById(exhibitionRoomId));
        }

        artifactRepository.persist(artifact);
        return Response.seeOther(URI.create("/artifacts")).build();
    }

    @POST
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        artifactRepository.deleteById(id);
        return Response.seeOther(URI.create("/artifacts")).build();
    }
}