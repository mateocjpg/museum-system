package com.museum.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "exhibition_history")
public class ExhibitionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artifact_id", nullable = false)
    private Artifact artifact;

    @ManyToOne
    @JoinColumn(name = "exhibition_room_id", nullable = false)
    private ExhibitionRoom room;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    public ExhibitionHistory() {}

    public ExhibitionHistory(Artifact artifact, ExhibitionRoom room, LocalDateTime startAt, LocalDateTime endAt) {
        this.artifact = artifact;
        this.room = room;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Artifact getArtifact() { return artifact; }
    public void setArtifact(Artifact artifact) { this.artifact = artifact; }

    public ExhibitionRoom getRoom() { return room; }
    public void setRoom(ExhibitionRoom room) { this.room = room; }

    public LocalDateTime getStartAt() { return startAt; }
    public void setStartAt(LocalDateTime startAt) { this.startAt = startAt; }

    public LocalDateTime getEndAt() { return endAt; }
    public void setEndAt(LocalDateTime endAt) { this.endAt = endAt; }
}