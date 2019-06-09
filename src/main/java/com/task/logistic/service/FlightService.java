package com.task.logistic.service;

import com.task.logistic.entity.Flight;
import com.task.logistic.repository.FlightRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Transactional
    public Flight getById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    @Transactional
    public Flight getFlightByClientId(Long id) {
        return flightRepository.findByClientId(id);
    }

    @Transactional
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Transactional
    public void save(Flight flight) {
        flightRepository.save(flight);
    }
}
