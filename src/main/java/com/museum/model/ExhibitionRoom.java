package com.museum.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exhibition_room")
public class ExhibitionRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "museum_id", nullable = false)
    private Museum museum;

    public ExhibitionRoom() {}

    public ExhibitionRoom(String name, String description, Integer capacity, Museum museum) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.museum = museum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Museum getMuseum() {
        return museum;
    }

    public void setMuseum(Museum museum) {
        this.museum = museum;
    }
}