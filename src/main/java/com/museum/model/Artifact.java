package com.museum.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "artifact")
public class Artifact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "acquired_at")
    private LocalDateTime acquiredAt;

    @Column(name = "conservation_percentage")
    private Integer conservationPercentage;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "exhibition_room_id")
    private ExhibitionRoom currentRoom;

    public Artifact() {}

    public Artifact(String name, String description, String imageUrl, LocalDateTime createdAt, 
                    LocalDateTime acquiredAt, Integer conservationPercentage, 
                    Category category, Artist artist, ExhibitionRoom currentRoom) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.acquiredAt = acquiredAt;
        this.conservationPercentage = conservationPercentage;
        this.category = category;
        this.artist = artist;
        this.currentRoom = currentRoom;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getAcquiredAt() { return acquiredAt; }
    public void setAcquiredAt(LocalDateTime acquiredAt) { this.acquiredAt = acquiredAt; }

    public Integer getConservationPercentage() { return conservationPercentage; }
    public void setConservationPercentage(Integer conservationPercentage) { this.conservationPercentage = conservationPercentage; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Artist getArtist() { return artist; }
    public void setArtist(Artist artist) { this.artist = artist; }

    public ExhibitionRoom getCurrentRoom() { return currentRoom; }
    public void setCurrentRoom(ExhibitionRoom currentRoom) { this.currentRoom = currentRoom; }
}