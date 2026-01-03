package com.jfCasino.rulette_service.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.jfCasino.rulette_service.Client.WalletClient;
import com.jfCasino.rulette_service.Domain.RouletteBet;
import com.jfCasino.rulette_service.Entities.MultiBet;
import com.jfCasino.rulette_service.Mapper.MultiBetMapper;
import com.jfCasino.rulette_service.Repository.MultiBetRepository;
import com.jfCasino.rulette_service.dto.internal.WalletReserveResponse;
import com.jfCasino.rulette_service.dto.internal.WalletCommitRequest;
import com.jfCasino.rulette_service.dto.internal.WalletCommitResponse;
import com.jfCasino.rulette_service.dto.internal.WalletReserveRequest;
import com.jfCasino.rulette_service.dto.response.MultiBetResponse;
import com.jfCasino.rulette_service.Domain.BetCreatedEvent;

import java.util.List;
import java.security.SecureRandom;


@Service
public class RuletteService {

    private final SecureRandom secureRandom;

    private final WalletClient walletClient;

    private final MultiBetRepository multiBetRepository;
    
    private final MultiBetMapper mapper;

    private final KafkaTemplate<String, BetCreatedEvent> kafkaTemplate;
    
    public static final int ROULETTE_SIZE = 36;
    public static final int ROULETTE_ZERO = 0;
    public static final int ROULETTE_1ST = 12;
    public static final int ROULETTE_2ND = 24;

    public RuletteService(SecureRandom secureRandom, WalletClient walletClient,
         MultiBetRepository multiBetRepository, MultiBetMapper mapper, KafkaTemplate<String, BetCreatedEvent> kafkaTemplate) {
        this.secureRandom = secureRandom;
        this.walletClient = walletClient;
        this.multiBetRepository = multiBetRepository;
        this.mapper = mapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public MultiBetResponse placeBet(String userID, List<RouletteBet> bets) {
        //JF create WalletReserveRequest from bets total amount
        int totalBetAmount = bets.stream().mapToInt(RouletteBet::getAmount).sum();
        WalletReserveRequest reserveRequest = new WalletReserveRequest(userID, totalBetAmount);

        //JF call wallet/reserve
        WalletReserveResponse walletReserveResponse = walletClient.postReserve(reserveRequest).getBody();
        //JF check response status -> failed when insufficient funds
        if (walletReserveResponse == null || !walletReserveResponse.getStatus().equals(WalletReserveResponse.STATUS_PENDING)) {
            //JF handle failed reservation (e.g., insufficient funds)
            throw new RuntimeException("Wallet reservation failed");
        }

        //JF spin the roulette
        int spinResultNumber = secureRandom.nextInt(ROULETTE_SIZE + 1); //0-36
        String spinResultColor = (spinResultNumber == ROULETTE_ZERO) ? "GREEN" : (spinResultNumber % 2 == 0) ? "BLACK" : "RED";
        String odd_even = (spinResultNumber == ROULETTE_ZERO) ? "NONE" : (spinResultNumber % 2 == 0) ? "EVEN" : "ODD";
        String thirds = (spinResultNumber == ROULETTE_ZERO) ? "NONE" :
                (spinResultNumber <= ROULETTE_1ST) ? "1ST" :
                (spinResultNumber <= ROULETTE_2ND) ? "2ND" : "3RD";

        //JF create response object
        MultiBetResponse response = MultiBetMapper.MultiBetResponseFactory(userID, spinResultColor, spinResultNumber, bets, odd_even, thirds);

        //JF commit the winnings to wallet
        WalletCommitRequest commitRequest = new WalletCommitRequest(walletReserveResponse.getReservationID(), userID, response.getTotalWinnings());
        WalletCommitResponse walletCommit = walletClient.postCommit(commitRequest).getBody();

        //JF check if commit was successful
        if(walletCommit == null) {
            throw new RuntimeException("Wallet commit failed");
        }

        //TODO ensure transactionality!!!!!!!!!!
        //JF save to DB
        MultiBet entity = MultiBetMapper.toEntity(response);
        multiBetRepository.save(entity);

        //publish event to Kafka
        BetCreatedEvent event = new BetCreatedEvent(
            entity.getId(),
            entity.getUserId(),
            "roulette",
            totalBetAmount,
            response.getTotalWinnings(),
            entity.getCreatedAt()
        );

        //poslji na kafko
        kafkaTemplate.send("bets-topic", event);

        return response;
    }

    public Page<MultiBetResponse> getListOfBets(String direction, int limit) {
        Sort sort = direction.equalsIgnoreCase("desc")
        ? Sort.by("createdAt").descending()
        : Sort.by("createdAt").ascending();

        Pageable pageable = PageRequest.of(0, limit, sort);

        Page<MultiBet> page = multiBetRepository.findAll(pageable);

        //transform to response object
        Page<MultiBetResponse> response = page.map(MultiBetMapper::toDto);

        return response; 
    }
}
