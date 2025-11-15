package com.jfCasino.rulette_service.dto.internal;

public class WalletReserveRequest {
    private int userID;
    private int amount;

    public WalletReserveRequest() {};

    public WalletReserveRequest(int userID, int amount) {
        this.userID = userID;
        this.amount = amount;
    }
    //getters and setters
    
    public int getAmount() {
        return amount;
    }

    public int getUserID() {
        return userID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
