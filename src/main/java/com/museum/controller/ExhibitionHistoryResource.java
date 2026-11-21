package com.museum.controller;

import com.museum.model.ExhibitionHistory;
import com.museum.repository.ArtifactRepository;
import com.museum.repository.ExhibitionHistoryRepository;
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

@Path("/history")
public class ExhibitionHistoryResource {

    @Inject
    Template history;

    @Inject
    ExhibitionHistoryRepository historyRepository;

    @Inject
    ArtifactRepository artifactRepository;

    @Inject
    ExhibitionRoomRepository roomRepository;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance list() {
        return history
                .data("histories", historyRepository.listAll())
                .data("artifacts", artifactRepository.listAll())
                .data("rooms", roomRepository.listAll());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("artifactId") Long artifactId,
                           @FormParam("exhibitionRoomId") Long exhibitionRoomId,
                           @FormParam("startAt") String startAt,
                           @FormParam("endAt") String endAt) {
        ExhibitionHistory record = new ExhibitionHistory();
        if (artifactId != null) {
            record.setArtifact(artifactRepository.findById(artifactId));
        }
        if (exhibitionRoomId != null) {
        record.setRoom(roomRepository.findById(exhibitionRoomId));        }
        if (startAt != null && !startAt.isBlank()) {
            record.setStartAt(LocalDateTime.parse(startAt));
        }
        if (endAt != null && !endAt.isBlank()) {
            record.setEndAt(LocalDateTime.parse(endAt));
        }
        historyRepository.persist(record);
        return Response.seeOther(URI.create("/history")).build();
    }

    @POST
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        historyRepository.deleteById(id);
        return Response.seeOther(URI.create("/history")).build();
    }
}