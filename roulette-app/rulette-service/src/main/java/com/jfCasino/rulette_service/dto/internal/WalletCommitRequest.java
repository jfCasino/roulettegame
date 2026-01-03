package com.jfCasino.rulette_service.dto.internal;


import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(
    name = "WalletCommitRequest",
    description = "Request sent to the wallet service to commit a previously reserved wallet transaction"
)
public class WalletCommitRequest {

    @Schema(
        description = "Identifier of the wallet reservation to be committed",
        example = "res-987654"
    )
    @NotBlank
    private String reservationID;

    @Schema(
        description = "Unique identifier of the user whose wallet is being charged",
        example = "user-12345"
    )
    @NotBlank
    private String userID;

    @Schema(
        description = "Final amount to be charged from the reserved funds",
        example = "150",
        minimum = "0"
    )
    @Min(0)
    private int amount;

    public WalletCommitRequest() {};

    public WalletCommitRequest(String reservationID, String userID, int amount) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.amount = amount;
    }

    //getters and setters
    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }   

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
