package com.jfCasino.rulette_service.Repository;

import com.jfCasino.rulette_service.Entities.MultiBet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MultiBetRepository extends JpaRepository<MultiBet, UUID> {
}
