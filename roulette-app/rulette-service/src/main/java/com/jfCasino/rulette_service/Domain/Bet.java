package com.jfCasino.rulette_service.Domain;

public class Bet {
    private String betType; // eg. "NUMBER", "COLOR", "ODD_EVEN", "THIRDS
    private String target;  // eg. "17", "RED", "ODD", "1ST"
    private int amount;

    public Bet() {}

    public Bet(String betType, String target, int amount) {
        this.betType = betType;
        this.target = target;
        this.amount = amount;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
