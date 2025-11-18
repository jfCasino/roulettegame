package com.jfCasino.rulette_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jfCasino.rulette_service.Domain.Bet;
import com.jfCasino.rulette_service.dto.request.MultiBetRequest;
import com.jfCasino.rulette_service.dto.response.MultiBetResponse;
import com.jfCasino.rulette_service.Service.RuletteService;

import java.util.List;

@RestController
public class RuletteController {

    private final RuletteService ruletteService;

    @Autowired
    public RuletteController(RuletteService ruletteService) {
        this.ruletteService = ruletteService;
    }

    @PostMapping("/rulette/bet")
    public ResponseEntity<MultiBetResponse> placeBet(@RequestBody MultiBetRequest request) {
        //JF changes DTO SingleBetRequest to Domain bets
        List<Bet> bets = request.getBets().stream()
            .map(betRequest -> new Bet(betRequest.getBetType(), betRequest.getTarget(), betRequest.getAmount()))
            .toList();

        MultiBetResponse response = ruletteService.placeBet(request.getUserID(), bets);

        
        return ResponseEntity.ok(response);
    }

    //TODO eventualy replace with communication via Kafka
    @GetMapping("/rulette/bets")
    public ResponseEntity<List<MultiBetResponse>> getBets(@RequestParam(name = "order", defaultValue = "asc") String order,
     @RequestParam(name= "limit", defaultValue = "10") int limit) {

        List<MultiBetResponse> bets = ruletteService.getListOfBets(order, limit);    
        
        return ResponseEntity.ok(bets);
    }
}
