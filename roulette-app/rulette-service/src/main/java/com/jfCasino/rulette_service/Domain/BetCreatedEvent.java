package com.jfCasino.rulette_service.Domain;

import java.time.Instant;
import java.util.UUID;
import java.math.BigDecimal;

public class BetCreatedEvent {
    private UUID id;
    private String userId;
    private String gameId;
    private BigDecimal totalStake;
    private BigDecimal totalWinnings;
    private Instant createdAt;

    // Constructors
    public BetCreatedEvent() {
    }

    public BetCreatedEvent(UUID id, String userId, String gameId, BigDecimal totalStake, BigDecimal totalWinnings, Instant createdAt) {
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

    public BigDecimal getTotalStake() {
        return totalStake;
    }

    public void setTotalStake(BigDecimal totalStake) {
        this.totalStake = totalStake;
    }

    public BigDecimal getTotalWinnings() {
        return totalWinnings;
    }

    public void setTotalWinnings(BigDecimal totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
