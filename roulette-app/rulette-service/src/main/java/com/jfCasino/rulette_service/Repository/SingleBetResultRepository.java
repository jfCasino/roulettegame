package com.jfCasino.rulette_service.Repository;

import com.jfCasino.rulette_service.Entities.SingleBetResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SingleBetResultRepository extends JpaRepository<SingleBetResult, UUID> {
}
