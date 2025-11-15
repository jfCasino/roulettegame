package com.jfCasino.rulette_service.dto.response;
import java.util.List;

public class MultiBetResponse {
    private String userID;
    private String spinResultColor;
    private int spinResultNumber;
    private List<SingleBetResult> betResults;
    private int totalWinnings;

    public MultiBetResponse() {}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSpinResultColor() {
        return spinResultColor;
    }

    public void setSpinResultColor(String spinResultColor) {
        this.spinResultColor = spinResultColor;
    }

    public int getSpinResultNumber() {
        return spinResultNumber;
    }

    public void setSpinResultNumber(int spinResultNumber) {
        this.spinResultNumber = spinResultNumber;
    }

    public List<SingleBetResult> getBetResults() {
        return betResults;
    }

    public void setBetResults(List<SingleBetResult> betResults) {
        this.betResults = betResults;
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }

    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    static public class SingleBetResult {
        private String betType;
        private String target;
        private int amount;
        private boolean isWin;
        private int payout; //amount + winnings

        public SingleBetResult() {}

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

        public boolean getIsWin() {
            return isWin;
        }

        public void setIsWin(boolean isWin) {
            this.isWin = isWin;
        }

        public int getPayout() {
            return payout;
        }

        public void setPayout(int payout) {
            this.payout = payout;
        }
    }
}
