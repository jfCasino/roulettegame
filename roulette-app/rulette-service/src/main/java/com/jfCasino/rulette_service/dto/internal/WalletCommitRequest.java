package com.jfCasino.rulette_service.dto.internal;

public class WalletCommitRequest {
    private String reservationID;
    private int userID;
    private int amount;

    public WalletCommitRequest() {};

    public WalletCommitRequest(String reservationID, int userID, int amount) {
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
