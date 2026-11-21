package com.museum.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tour_room")
public class TourRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "exhibition_room_id", nullable = false)
    private ExhibitionRoom room;

    @Column(name = "sequence_number")
    private Integer sequenceNumber;

    public TourRoom() {}

    public TourRoom(Tour tour, ExhibitionRoom room, Integer sequenceNumber) {
        this.tour = tour;
        this.room = room;
        this.sequenceNumber = sequenceNumber;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Tour getTour() { return tour; }
    public void setTour(Tour tour) { this.tour = tour; }

    public ExhibitionRoom getRoom() { return room; }
    public void setRoom(ExhibitionRoom room) { this.room = room; }

    public Integer getSequenceNumber() { return sequenceNumber; }
    public void setSequenceNumber(Integer sequenceNumber) { this.sequenceNumber = sequenceNumber; }
}