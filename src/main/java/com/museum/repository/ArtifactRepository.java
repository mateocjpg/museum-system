package com.museum.repository;

import com.museum.model.Artifact;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtifactRepository implements PanacheRepository<Artifact> {
}
