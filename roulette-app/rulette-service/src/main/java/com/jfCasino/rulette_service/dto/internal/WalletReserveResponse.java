package com.jfCasino.rulette_service.dto.internal;

public class WalletReserveResponse {

    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_FAILED = "FAILED";

    private String reservationID;
    private String userID;
    private int amount;
    private String status; //e.g., "PENDING", "FAILED"

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
