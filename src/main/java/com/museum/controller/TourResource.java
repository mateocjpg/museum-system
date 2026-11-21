package com.museum.controller;

import com.museum.model.Tour;
import com.museum.model.TourRoom;
import com.museum.repository.ExhibitionRoomRepository;
import com.museum.repository.TourRepository;
import com.museum.repository.TourRoomRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.time.LocalDateTime;

@Path("/tours")
public class TourResource {

    @Inject
    TourRepository tourRepository;

    @Inject
    ExhibitionRoomRepository roomRepository;

    @Inject
    TourRoomRepository tourRoomRepository;

    @Inject
    Template tours;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance list() {
        return tours.data("tours", tourRepository.listAll())
                    .data("rooms", roomRepository.listAll())
                    .data("tourRooms", tourRoomRepository.listAll());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response save(@FormParam("name") String name,
                         @FormParam("description") String description,
                         @FormParam("landingPageUrl") String landingPageUrl,
                         @FormParam("startAt") String startAt,
                         @FormParam("endAt") String endAt) {
        Tour tour = new Tour();
        tour.setName(name);
        tour.setDescription(description);
        tour.setLandingPageUrl(landingPageUrl);
        if (startAt != null && !startAt.isBlank()) tour.setStartAt(LocalDateTime.parse(startAt));
        if (endAt != null && !endAt.isBlank()) tour.setEndAt(LocalDateTime.parse(endAt));
        tourRepository.persist(tour);
        return Response.seeOther(URI.create("/tours")).build();
    }

    @POST
    @Path("/assign-room")
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response assignRoom(@FormParam("tourId") Long tourId,
                               @FormParam("exhibitionRoomId") Long exhibitionRoomId,
                               @FormParam("sequenceNumber") Integer sequenceNumber) {
        Tour tour = tourRepository.findById(tourId);
        var room = roomRepository.findById(exhibitionRoomId);
        if (tour != null && room != null) {
            tourRoomRepository.persist(new TourRoom(tour, room, sequenceNumber));
        }
        return Response.seeOther(URI.create("/tours")).build();
    }

    @POST
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        tourRoomRepository.delete("tour.id", id);
        tourRepository.deleteById(id);
        return Response.seeOther(URI.create("/tours")).build();
    }
}
