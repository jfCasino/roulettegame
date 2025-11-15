package com.jfCasino.rulette_service.dto.internal;

public class WalletReserveRequest {
    private String userID;
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
