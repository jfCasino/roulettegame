package com.jfCasino.rulette_service.dto.internal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "WalletReserveResponse",
    description = "Response returned after reserving funds in a wallet"
)
public class WalletReserveResponse {

    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_FAILED = "FAILED";

    @Schema(
        description = "Unique identifier of the reservation",
        example = "res-987654"
    )
    private String reservationID;

    @Schema(
        description = "Unique identifier of the user whose funds are reserved",
        example = "user-12345"
    )
    private String userID;

    @Schema(
        description = "Amount of funds reserved",
        example = "150"
    )
    private int amount;

    @Schema(
        description = "Current status of the reservation",
        example = "PENDING",
        allowableValues = { "PENDING", "FAILED" }
    )
    private String status;

    public WalletReserveResponse() {}

    public WalletReserveResponse(String reservationID, String userID, int amount, String status) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.amount = amount;
        this.status = status;
    }

    public String getReservationID() {
        return reservationID;
    }

    public String getUserID() {
        return userID;
    }

    public int getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
