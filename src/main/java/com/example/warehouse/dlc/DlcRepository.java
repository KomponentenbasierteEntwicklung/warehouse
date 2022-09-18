package com.example.warehouse.dlc;

import com.example.warehouse.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DlcRepository extends JpaRepository<Dlc, Long> {
    @Query("SELECT g FROM Dlc g WHERE g.name = ?1 ")
    Optional<Dlc> findDlcByName(String name);

    Boolean existsByName(String name);
}
