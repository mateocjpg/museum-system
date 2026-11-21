package com.museum.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class MuseumObject extends PanacheEntity {
    public String description;
    public String imageUrl;
    public LocalDate creationDate;
    public LocalDate acquisitionDate;
    public Integer conservationPercentage;

    @ManyToOne
    public Category category;

    @ManyToOne
    public Artist artist;

    @ManyToOne
    public ExhibitionRoom currentRoom;

    public MuseumObject() {}
}