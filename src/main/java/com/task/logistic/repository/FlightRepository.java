package com.task.logistic.repository;

import com.task.logistic.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query(value = "SELECT * FROM flight WHERE client_id = :id", nativeQuery = true)
    Flight findByClientId(@Param("id") Long id);

}