package com.jfCasino.rulette_service.dto.internal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "WalletCommitResponse",
    description = "Response returned after committing a wallet transaction"
)
public class WalletCommitResponse {

    @Schema(
        description = "Unique identifier of the commit operation",
        example = "commit-987654"
    )
    private String commitID;

    @Schema(
        description = "Identifier of the wallet reservation that was committed",
        example = "res-987654"
    )
    private String reservationID;

    @Schema(
        description = "Unique identifier of the user whose wallet was affected",
        example = "user-12345"
    )
    private String userID;

    @Schema(
        description = "Amount charged from the wallet",
        example = "150"
    )
    private int amount;

    @Schema(
        description = "New wallet balance after the commit",
        example = "850"
    )
    private int newBalance;

    public WalletCommitResponse() {}

    public WalletCommitResponse(String commitID, String reservationID, String userID, int amount, int newBalance) {
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

    public int getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(int newBalance) {
        this.newBalance = newBalance;
    }
}
