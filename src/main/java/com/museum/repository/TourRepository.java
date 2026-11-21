package com.museum.repository;

import com.museum.model.Tour;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TourRepository implements PanacheRepository<Tour> {
}