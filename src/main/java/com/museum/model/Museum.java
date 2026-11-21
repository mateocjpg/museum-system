package com.museum.model;

import jakarta.persistence.*;

@Entity
@Table(name = "museum")
public class Museum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double latitude;
    private Double longitude;

    @Column(name = "landing_page_url")
    private String landingPageUrl;

    public Museum() {}

    public Museum(String name, String description, Double latitude, Double longitude, String landingPageUrl) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.landingPageUrl = landingPageUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getLandingPageUrl() { return landingPageUrl; }
    public void setLandingPageUrl(String landingPageUrl) { this.landingPageUrl = landingPageUrl; }
}