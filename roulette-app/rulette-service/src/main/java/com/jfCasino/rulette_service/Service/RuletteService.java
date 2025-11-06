package com.jfCasino.rulette_service.Service;

import org.springframework.stereotype.Service;

import com.jfCasino.rulette_service.dto.MultiBetResponse;
import com.jfCasino.rulette_service.Domain.Bet;
import java.util.List;

@Service
public class RuletteService {

    public MultiBetResponse placeBet(int userID, List<Bet> bets) {
        //TODO implement method
        return new MultiBetResponse();
    }
}
