package com.jfCasino.rulette_service.dto.response;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "MultiBetResponse",
    description = "Response returned after resolving a roulette spin with multiple bets"
)
public class MultiBetResponse {
    @Schema(
        description = "Unique identifier of the user who placed the bets",
        example = "user-12345"
    )
    private String userID;

    @Schema(
        description = "Color result of the roulette spin",
        example = "RED",
        allowableValues = { "RED", "BLACK", "GREEN" }
    )
    private String spinResultColor;

    @Schema(
        description = "Number result of the roulette spin",
        example = "17",
        minimum = "0",
        maximum = "36"
    )
    private int spinResultNumber;

    @Schema(
        description = "Detailed results for each individual bet placed by the user"
    )
    private List<SingleBetResult> betResults;

    @Schema(
        description = "Total winnings from all bets combined",
        example = "250"
    )
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

    @Schema(
        name = "SingleBetResult",
        description = "Result of a single roulette bet"
    )
    public static class SingleBetResult {

        @Schema(
            description = "Type of the roulette bet",
            example = "NUMBER",
            allowableValues = {
                "NUMBER",
                "COLOR",
                "ODD_EVEN",
                "THIRDS"
            }
        )
        private String betType;

        @Schema(
            description = "Target value of the bet",
            example = "17"
        )
        private String target;

        @Schema(
            description = "Amount wagered on this bet",
            example = "50"
        )
        private int amount;

        @Schema(
            description = "Indicates whether the bet won",
            example = "true"
        )
        private boolean isWin;

        @Schema(
            description = "Total payout for this bet (stake + winnings)",
            example = "180"
        )
        private int payout;

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
