package com.jfCasino.rulette_service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(
    name = "MultiBetRequest",
    description = "Request payload for placing multiple roulette bets in a single spin"
)
public class MultiBetRequest {

    @Schema(
        description = "Unique identifier of the user placing the bets",
        example = "user-12345"
    )
    @NotBlank
    private String userID;

    @Schema(
        description = "List of individual bets placed by the user",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty
    @Valid
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


    @Schema(
        name = "SingleBetRequest",
        description = "Represents a single roulette bet"
    )
    static public class SingleBetRequest {

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
        @NotBlank
        private String betType; // eg. "NUMBER", "COLOR", "ODD_EVEN", "THIRDS

        @Schema(
            description = "Target value of the bet, depending on bet type",
            example = "17"
        )
        @NotBlank
        private String target;  // eg. "17", "RED", "ODD", "1ST"

        @Schema(
            description = "Amount of money wagered on this bet",
            example = "50",
            minimum = "1"
        )
        @Min(1)
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
