package com.tklimczak.websocket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tklimczak.websocket.model.StateEntity;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {
    Optional<StateEntity> findById(Long tableId);
}
