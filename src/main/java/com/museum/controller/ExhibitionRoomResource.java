package com.museum.controller;

import com.museum.model.ExhibitionRoom;
import com.museum.repository.ExhibitionRoomRepository;
import com.museum.repository.MuseumRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/rooms")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class ExhibitionRoomResource {

    @Inject
    ExhibitionRoomRepository roomRepository;

    @Inject
    MuseumRepository museumRepository;

    @Inject
    Template rooms; 

    @GET
    public TemplateInstance list() {
        return rooms.data("rooms", roomRepository.listAll())
                    .data("museums", museumRepository.listAll());
    }

    @POST
    @Transactional
    public Response save(@FormParam("name") String name,
                         @FormParam("description") String description,
                         @FormParam("capacity") Integer capacity,
                         @FormParam("museumId") Long museumId) {
        
        ExhibitionRoom room = new ExhibitionRoom();
        room.setName(name);
        room.setDescription(description);
        room.setCapacity(capacity);
        if (museumId != null) {
            room.setMuseum(museumRepository.findById(museumId));
        }

        roomRepository.persist(room);
        return Response.seeOther(URI.create("/rooms")).build();
    }

    @POST
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        roomRepository.deleteById(id);
        return Response.seeOther(URI.create("/rooms")).build();
    }
}