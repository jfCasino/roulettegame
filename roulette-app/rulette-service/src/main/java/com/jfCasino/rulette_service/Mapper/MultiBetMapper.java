package com.jfCasino.rulette_service.Mapper;

import com.jfCasino.rulette_service.Domain.RouletteBet;
import com.jfCasino.rulette_service.Entities.MultiBet;
import com.jfCasino.rulette_service.Entities.SingleBetResult;
import com.jfCasino.rulette_service.dto.response.MultiBetResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MultiBetMapper {

    public static MultiBet toEntity(MultiBetResponse dto) {

        MultiBet entity = new MultiBet();
        entity.setUserId(dto.getUserID());
        entity.setSpinResultColor(dto.getSpinResultColor());
        entity.setSpinResultNumber(dto.getSpinResultNumber());
        entity.setTotalWinnings(dto.getTotalWinnings());

        dto.getBetResults().forEach(r -> {
            SingleBetResult bet = new SingleBetResult();
            bet.setBetType(r.getBetType());
            bet.setTarget(r.getTarget());
            bet.setAmount(r.getAmount());
            bet.setWin(r.getIsWin());
            bet.setPayout(r.getPayout());

            bet.setMultiBet(entity);  
            entity.getBetResults().add(bet); 
        });

        return entity;
    }

    public static MultiBetResponse MultiBetResponseFactory(String userID, String spinResultColor, int spinResultNumber,
     java.util.List<RouletteBet> bets, String odd_even, String thirds) {
        MultiBetResponse response = new MultiBetResponse();
        response.setUserID(userID);
        response.setSpinResultColor(spinResultColor);
        response.setSpinResultNumber(spinResultNumber);
        response.setBetResults(new java.util.ArrayList<>());
        int totalWinnings = 0;
        for (RouletteBet bet : bets) {
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

        return response;
    }

    public static MultiBetResponse toDto(MultiBet multiBet) {
        MultiBetResponse response = new MultiBetResponse();
        response.setUserID(multiBet.getUserId());
        response.setSpinResultNumber(multiBet.getSpinResultNumber());
        response.setSpinResultColor(multiBet.getSpinResultColor());
        response.setTotalWinnings(multiBet.getTotalWinnings());
        
        List<MultiBetResponse.SingleBetResult> betResults = multiBet.getBetResults()
            .stream()
            .map(MultiBetMapper::mapSingleBet)
            .toList();

        response.setBetResults(betResults);

        return response;
    }

    private static MultiBetResponse.SingleBetResult mapSingleBet(SingleBetResult entity) {
        MultiBetResponse.SingleBetResult dto = new MultiBetResponse.SingleBetResult();

        dto.setBetType(entity.getBetType());
        dto.setTarget(entity.getTarget());
        dto.setAmount(entity.getAmount());
        dto.setIsWin(entity.isWin());
        dto.setPayout(entity.getPayout());

        return dto;
    }
}
