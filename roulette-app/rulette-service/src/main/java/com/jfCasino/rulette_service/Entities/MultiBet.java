package com.jfCasino.rulette_service.Entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "multi_bet")
@EntityListeners(AuditingEntityListener.class)
public class MultiBet {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    private String userId;

    private String spinResultColor;

    private int spinResultNumber;

    private int totalWinnings;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "multiBet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SingleBetResult> betResults = new ArrayList<>();


    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSpinResultColor() {
        return spinResultColor;
    }

    public void setSpinResultColor(String spinResultColor) {
        this.spinResultColor = spinResultColor;
    }

    public int getSpinResultNumber() {
        return spinResultNumber;
    }

    public void setSpinResultNumber(int spinResultNumber) {
        this.spinResultNumber = spinResultNumber;
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }

    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<SingleBetResult> getBetResults() {
        return betResults;
    }

    public void setBetResults(List<SingleBetResult> betResults) {
        this.betResults = betResults;
    }   
}