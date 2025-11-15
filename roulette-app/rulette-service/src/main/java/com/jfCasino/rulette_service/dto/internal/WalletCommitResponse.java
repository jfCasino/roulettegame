package com.jfCasino.rulette_service.dto.internal;

public class WalletCommitResponse {
    private String commitID;
    private String reservationID;
    private int userID;
    private int amount;
    private int newBalance;

    public WalletCommitResponse() {}

    public WalletCommitResponse(String commitID, String reservationID, int userID, int amount, int newBalance) {
        this.commitID = commitID;
        this.reservationID = reservationID;
        this.userID = userID;
        this.amount = amount;
        this.newBalance = newBalance;
    }

    //getters and setters
    public String getCommitID() {
        return commitID;
    }

    public void setCommitID(String commitID) {
        this.commitID = commitID;
    }

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

    public int getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(int newBalance) {
        this.newBalance = newBalance;
    }
}
