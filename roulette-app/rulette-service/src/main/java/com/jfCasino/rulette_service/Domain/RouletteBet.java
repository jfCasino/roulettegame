package com.jfCasino.rulette_service.Domain;

public class RouletteBet {
    private String betType; // eg. "NUMBER", "COLOR", "ODD_EVEN", "THIRDS
    private String target;  // eg. "17", "RED", "ODD", "1ST"
    private int amount;

    public static final int PAYOUT_NUMBER = 36;
    public static final int PAYOUT_COLOR = 2;
    public static final int PAYOUT_ODD_EVEN = 2;
    public static final int PAYOUT_THIRDS = 3;

    public RouletteBet() {}

    public RouletteBet(String betType, String target, int amount) {
        this.betType = betType;
        this.target = target;
        this.amount = amount;
    }

    public int getPayout(String spinResultColor, String spinResultNumber, String odd_even, String thirds) {
        switch (betType) {
            case "NUMBER":
                if (this.target.equals(spinResultNumber)) {
                    return this.amount * PAYOUT_NUMBER;
                }
                break;
            case "COLOR":
                if (this.target.equals(spinResultColor)) {
                    return this.amount * PAYOUT_COLOR;
                }
                break;
            case "ODD_EVEN":
                if (this.target.equals(odd_even)) {
                    return this.amount * PAYOUT_ODD_EVEN;
                }
                break;
            case "THIRDS":
                if (this.target.equals(thirds)) {
                    return this.amount * PAYOUT_THIRDS;
                }
                break;
            default:
                return 0;
        }
        return 0;
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
