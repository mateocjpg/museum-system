package com.museum.controller;

import com.museum.model.Category;
import com.museum.repository.CategoryRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/categories")
public class CategoryResource {

    @Inject
    Template categories;

    @Inject
    CategoryRepository categoryRepository;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance list() {
        return categories.data("categories", categoryRepository.listAll());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("name") String name,
                           @FormParam("description") String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        categoryRepository.persist(category);
        return Response.seeOther(URI.create("/categories")).build();
    }

    @POST
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        categoryRepository.deleteById(id);
        return Response.seeOther(URI.create("/categories")).build();
    }
}