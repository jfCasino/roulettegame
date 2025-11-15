package com.jfCasino.rulette_service.Mapper;

import com.jfCasino.rulette_service.Entities.MultiBet;
import com.jfCasino.rulette_service.Entities.SingleBetResult;
import com.jfCasino.rulette_service.dto.response.MultiBetResponse;
import org.springframework.stereotype.Component;

@Component
public class MultiBetMapper {

    public MultiBet toEntity(MultiBetResponse dto) {

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
}
