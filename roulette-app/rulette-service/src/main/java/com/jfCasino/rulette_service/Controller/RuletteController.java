package com.jfCasino.rulette_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;


import com.jfCasino.rulette_service.Domain.Bet;
import com.jfCasino.rulette_service.dto.request.MultiBetRequest;
import com.jfCasino.rulette_service.dto.response.MultiBetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.jfCasino.rulette_service.Service.RuletteService;

import java.util.List;

@RestController
@Tag(name = "Roulette Service", description = "Endpoints for placing and retrieving roulette bets")
public class RuletteController {

    private final RuletteService ruletteService;

    @Autowired
    public RuletteController(RuletteService ruletteService) {
        this.ruletteService = ruletteService;
    }

    @PostMapping("/rulette/bet")
    @Operation(summary = "Place multiple bets for a roulette spin",
               description = "Accepts a MultiBetRequest and returns a MultiBetResponse including results and payouts")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bets successfully resolved"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
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
    @Operation(summary = "Retrieve paginated list of roulette bets",
               description = "Returns a page of MultiBetResponse objects. Can be ordered ascending or descending, limited by a parameter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paginated bets retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid query parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Page<MultiBetResponse>> getBets(@RequestParam(name = "order", defaultValue = "asc") String order,
     @RequestParam(name= "limit", defaultValue = "10") int limit) {

        Page<MultiBetResponse> bets = ruletteService.getListOfBets(order, limit);    
        
        return ResponseEntity.ok(bets);
    }
}
