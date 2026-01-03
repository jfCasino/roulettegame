package com.jfCasino.rulette_service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jfCasino.rulette_service.dto.internal.WalletCommitRequest;
import com.jfCasino.rulette_service.dto.internal.WalletCommitResponse;
import com.jfCasino.rulette_service.dto.internal.WalletReserveRequest;
import com.jfCasino.rulette_service.dto.internal.WalletReserveResponse;

@FeignClient(
    name = "wallet-service",
    url = "${wallet.service.url}"  // will be set via env or application.yml
)
public interface WalletClient {
    @PostMapping("/wallets/reserve")
    public ResponseEntity<WalletReserveResponse> postReserve(@RequestBody WalletReserveRequest request);

    @PostMapping("/wallets/commit")
    public ResponseEntity<WalletCommitResponse> postCommit(@RequestBody WalletCommitRequest request);

}
