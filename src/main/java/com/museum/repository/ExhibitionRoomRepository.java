package com.museum.repository;

import com.museum.model.ExhibitionRoom;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExhibitionRoomRepository implements PanacheRepository<ExhibitionRoom> {
}