package com.museum.repository;

import com.museum.model.ExhibitionHistory;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExhibitionHistoryRepository implements PanacheRepository<ExhibitionHistory> {
}