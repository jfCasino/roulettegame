package com.jfCasino.rulette_service.Domain;

import java.time.Instant;
import java.util.UUID;

public class BetCreatedEvent {
    private UUID id;
    private String userId;
    private String gameId;
    private int totalStake;
    private int totalWinnings;
    private Instant createdAt;

    // Constructors
    public BetCreatedEvent() {
    }

    public BetCreatedEvent(UUID id, String userId, String gameId, int totalStake, int totalWinnings, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.totalStake = totalStake;
        this.totalWinnings = totalWinnings;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getTotalStake() {
        return totalStake;
    }

    public void setTotalStake(int totalStake) {
        this.totalStake = totalStake;
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
}
