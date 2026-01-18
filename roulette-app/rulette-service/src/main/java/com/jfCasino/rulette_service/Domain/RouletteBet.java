package com.jfCasino.rulette_service.Domain;

import java.math.BigDecimal;

public class RouletteBet {
    private String betType; // eg. "NUMBER", "COLOR", "ODD_EVEN", "THIRDS
    private String target;  // eg. "17", "RED", "ODD", "1ST"
    private BigDecimal amount;

    public static final BigDecimal PAYOUT_NUMBER = BigDecimal.valueOf(36);
    public static final BigDecimal PAYOUT_COLOR = BigDecimal.valueOf(2);
    public static final BigDecimal PAYOUT_ODD_EVEN = BigDecimal.valueOf(2);
    public static final BigDecimal PAYOUT_THIRDS = BigDecimal.valueOf(3);

    public RouletteBet() {}

    public RouletteBet(String betType, String target, BigDecimal amount) {
        this.betType = betType;
        this.target = target;
        this.amount = amount;
    }

    public BigDecimal getPayout(String spinResultColor, String spinResultNumber, String odd_even, String thirds) {
        switch (betType) {
            case "NUMBER":
                if (this.target.equals(spinResultNumber)) {
                    return this.amount.multiply(PAYOUT_NUMBER);
                }
                break;
            case "COLOR":
                if (this.target.equals(spinResultColor)) {
                    return this.amount.multiply(PAYOUT_COLOR);
                }
                break;
            case "ODD_EVEN":
                if (this.target.equals(odd_even)) {
                    return this.amount.multiply(PAYOUT_ODD_EVEN);
                }
                break;
            case "THIRDS":
                if (this.target.equals(thirds)) {
                    return this.amount.multiply(PAYOUT_THIRDS);
                }
                break;
            default:
                return BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
