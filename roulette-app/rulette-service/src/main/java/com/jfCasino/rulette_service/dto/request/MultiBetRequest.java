package com.jfCasino.rulette_service.dto.request;

import java.util.List;

public class MultiBetRequest {
    private String userID;
    private List<SingleBetRequest> bets;

    public MultiBetRequest() {}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<SingleBetRequest> getBets() {
        return bets;
    }

    public void setBets(List<SingleBetRequest> bets) {
        this.bets = bets;
    }

    static public class SingleBetRequest {
        private String betType; // eg. "NUMBER", "COLOR", "ODD_EVEN", "THIRDS
        private String target;  // eg. "17", "RED", "ODD", "1ST"
        private int amount;

        public SingleBetRequest() {}

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
}
