package com.jfCasino.rulette_service.Controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jfCasino.rulette_service.Domain.Bet;
import com.jfCasino.rulette_service.dto.MultiBetRequest;
import com.jfCasino.rulette_service.dto.MultiBetResponse;
import com.jfCasino.rulette_service.Service.RuletteService;

import java.util.Map;
import java.util.List;

@Controller
public class RuletteController {

    private final RuletteService ruletteService;

    @Autowired
    public RuletteController(RuletteService ruletteService) {
        this.ruletteService = ruletteService;
    }

    @PostMapping("/rulette/bet")
    public ResponseEntity<Map<String, Object>> placeBet(@RequestBody MultiBetRequest request) {
        //JF changes DTO SingleBetRequest to Domain bets
        List<Bet> bets = request.getBets().stream()
            .map(betRequest -> new Bet(betRequest.getBetType(), betRequest.getTarget(), betRequest.getAmount()))
            .toList();

        MultiBetResponse response = ruletteService.placeBet(request.getUserID(), bets);
        return ResponseEntity.ok(Map.of(
            "userID", request.getUserID(),
            "spinColor", response.getSpinResultColor(),
            "spinNumber", response.getSpinResultNumber(),
            "totalWinnings", response.getTotalWinnings(),
            "betResults", response.getBetResults()
        )   );
    }

    //TODO eventualy replace with communication via Kafka
    @GetMapping("/rulette/bets")
    public ResponseEntity<List<Object>> getBets(@RequestParam(name = "order", defaultValue = "asc") String order,
     @RequestParam(name= "limit", defaultValue = "10") int limit) {
        
        return;
    }
}
