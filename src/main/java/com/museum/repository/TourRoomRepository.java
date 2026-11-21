package com.museum.repository;

import com.museum.model.TourRoom;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TourRoomRepository implements PanacheRepository<TourRoom> {
}