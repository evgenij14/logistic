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

    /**
     * Поиск рейса по ID (Searching the flight by id)
     *
     * @param id ID рейса (Flight's id)
     * @return Возвращает объект рейса(Return flight's object)
     */
    @Transactional
    public Flight getById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    /**
     * Удаление рейса из БД (Deleting of flight from Database)
     *
     * @param id ID рейса (Flight's id)
     */
    @Transactional
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    /**
     * Поиск рейса по ID клиента (Searching flight by client's id)
     * @param id ID клиента (Client's id)
     * @return Возвращает объект рейса(Return flight's object)
     */
    @Transactional
    public Flight getFlightByClientId(Long id) {
        return flightRepository.findByClientId(id);
    }

    /**
     * Получение всех рейсов из БД (Getting all flights from Database)
     * @return Возвращает список рейсов(Return List of flights)
     */
    @Transactional
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    /**
     * Сохранение рейса в БД (Saving the flight in Database)
     * @param flight Объект рейса (The object of flight)
     */
    @Transactional
    public void save(Flight flight) {
        flightRepository.save(flight);
    }
}
