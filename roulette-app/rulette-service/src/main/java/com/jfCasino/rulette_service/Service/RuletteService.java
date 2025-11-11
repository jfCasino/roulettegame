package com.jfCasino.rulette_service.Service;

import org.springframework.stereotype.Service;

import com.jfCasino.rulette_service.dto.MultiBetResponse;
import com.jfCasino.rulette_service.Domain.Bet;

import java.util.List;
import java.security.SecureRandom;

@Service
public class RuletteService {

    private final SecureRandom secureRandom;
    
    public static final int ROULETTE_SIZE = 36;
    public static final int ROULETTE_ZERO = 0;
    public static final int ROULETTE_1ST = 12;
    public static final int ROULETTE_2ND = 24;

    public RuletteService(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
    }

    public MultiBetResponse placeBet(int userID, List<Bet> bets) {
        //TODO call wallet/reserve 


        int spinResultNumber = secureRandom.nextInt(ROULETTE_SIZE + 1); //0-36
        String spinResultColor = (spinResultNumber == ROULETTE_ZERO) ? "GREEN" : (spinResultNumber % 2 == 0) ? "BLACK" : "RED";
        String odd_even = (spinResultNumber == ROULETTE_ZERO) ? "NONE" : (spinResultNumber % 2 == 0) ? "EVEN" : "ODD";
        String thirds = (spinResultNumber == ROULETTE_ZERO) ? "NONE" :
                (spinResultNumber <= ROULETTE_1ST) ? "1ST" :
                (spinResultNumber <= ROULETTE_2ND) ? "2ND" : "3RD";

        MultiBetResponse response = new MultiBetResponse();
        response.setUserID(userID);
        response.setSpinResultColor(spinResultColor);
        response.setSpinResultNumber(spinResultNumber);
        response.setBetResults(new java.util.ArrayList<>());
        int totalWinnings = 0;
        for (Bet bet : bets) {
            MultiBetResponse.SingleBetResult betResult = new MultiBetResponse.SingleBetResult();
            betResult.setBetType(bet.getBetType());
            betResult.setTarget(bet.getTarget());
            betResult.setAmount(bet.getAmount());
            int payout = bet.getPayout(spinResultColor, String.valueOf(spinResultNumber), odd_even, thirds);
            totalWinnings += payout;
            betResult.setPayout(payout);
            betResult.setIsWin(betResult.getPayout() > 0);
            response.getBetResults().add(betResult);
        }
        response.setTotalWinnings(totalWinnings);

        //TODO call wallet/commit

        return response;
    }

    public List<MultiBetResponse> getListOfBets(String order, int limit) {
        //TODO implement method to get list of bets from DB with order and limit

        //mock implementation
                MultiBetResponse.SingleBetResult bet1 = new MultiBetResponse.SingleBetResult();
        bet1.setBetType("NUMBER");
        bet1.setTarget("17");
        bet1.setAmount(100);
        bet1.setIsWin(false);
        bet1.setPayout(0);

        MultiBetResponse.SingleBetResult bet2 = new MultiBetResponse.SingleBetResult();
        bet2.setBetType("COLOR");
        bet2.setTarget("BLACK");
        bet2.setAmount(100);
        bet2.setIsWin(true);
        bet2.setPayout(200);

        MultiBetResponse response = new MultiBetResponse();
        response.setUserID(1);
        response.setSpinResultColor("BLACK");
        response.setSpinResultNumber(3);
        response.setTotalWinnings(200);
        response.setBetResults(List.of(bet1, bet2));

        MultiBetResponse.SingleBetResult bet3 = new MultiBetResponse.SingleBetResult();
        bet3.setBetType("NUMBER");
        bet3.setTarget("16");
        bet3.setAmount(100);
        bet3.setIsWin(false);
        bet3.setPayout(0);

        MultiBetResponse.SingleBetResult bet4 = new MultiBetResponse.SingleBetResult();
        bet4.setBetType("COLOR");
        bet4.setTarget("BLACK");
        bet4.setAmount(100);
        bet4.setIsWin(true);
        bet4.setPayout(200);

        MultiBetResponse response2 = new MultiBetResponse();
        response2.setUserID(2);
        response2.setSpinResultColor("RED");
        response2.setSpinResultNumber(4);
        response2.setTotalWinnings(0);
        response2.setBetResults(List.of(bet3, bet4));
        return List.of(response, response2);
    }
}
