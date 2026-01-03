package com.jfCasino.rulette_service.dto.internal;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(
    name = "WalletReserveRequest",
    description = "Request sent to the wallet service to reserve funds for a roulette bet"
)
public class WalletReserveRequest {

    @Schema(
        description = "Unique identifier of the user whose wallet funds are being reserved",
        example = "user-12345"
    )
    @NotBlank
    private String userID;

    @Schema(
        description = "Amount of money to reserve from the user's wallet",
        example = "150",
        minimum = "1"
    )
    @Min(1)
    private int amount;

    public WalletReserveRequest() {};

    public WalletReserveRequest(String userID, int amount) {
        this.userID = userID;
        this.amount = amount;
    }
    //getters and setters
    
    public int getAmount() {
        return amount;
    }

    public String getUserID() {
        return userID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
