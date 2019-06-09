package com.task.logistic.controller;

import com.task.logistic.entity.Flight;
import com.task.logistic.service.ClientService;
import com.task.logistic.service.CrewService;
import com.task.logistic.service.FlightService;
import com.task.logistic.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Контроллер для обслуживания таблицы рейсов (Controller for the maintenance of the table of flights)
 * Принимает запросы на url : "/flight" (Accepts requests on url : "/flight")
 */
@RestController
@RequestMapping("/flight")
public class FlightRestController {
    private final CrewService crewService;
    private final ClientService clientService;
    private final VehicleService vehicleService;
    private final FlightService flightService;

    public FlightRestController(FlightService flightService, VehicleService vehicleService, ClientService clientService, CrewService crewService) {
        this.flightService = flightService;
        this.vehicleService = vehicleService;
        this.clientService = clientService;
        this.crewService = crewService;
    }

    /**
     * Получение всех рейсов из БД (Getting all flights from Database)
     *
     * @return Возвращает список рейсов в формате JSON + статус ответа (Return List of flights on JSON + response status)
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Flight>> getAll() {
        List<Flight> flights = flightService.getAllFlights();
        if (!flights.isEmpty()) {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Поиск рейса по ID (Searching the flight by id)
     *
     * @param id ID рейса (Flight's id)
     * @return Возвращает объект рейса в формате JSON + статус ответа (Return flight's object on JSON + response status)
     */
    @GetMapping("/get-by-id")
    public ResponseEntity<Flight> getById(@RequestParam("id") Long id) {
        Flight flight = flightService.getById(id);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Поиск рейса по ID клиента (Searching flight by client's id)
     * @param id ID клиента (Client's id)
     * @return Возвращает объект рейса в формате JSON + статус ответа (Return flight's object on JSON + response status)
     */
    @GetMapping("/get-by-client-id")
    public ResponseEntity<Flight> getByClientId(@RequestParam("client_id") Long id) {
        Flight flight = flightService.getFlightByClientId(id);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Сохранение рейса в БД (Saving flight in Database)
     * @param vehicleId ID ТС (Vehicle's id)
     * @param crewId ID экипажа (Crew's id)
     * @param clientId ID клиента (Client's id)
     * @param sending Дата и время отправки (DateTime of sending)
     * @param getting Дата и время доставки/получения (DateTime of getting)
     * @return Возвращает сохраненный рейс в формате JSON + статус ответа (Return saved flight on JSON + response status)
     */
    @PostMapping("/save")
    public ResponseEntity<Flight> save(@RequestParam("vehicle_id") Long vehicleId,
                                       @RequestParam("crew_id") Long crewId,
                                       @RequestParam("client_id") Long clientId,
                                       @RequestParam("sending") Timestamp sending,
                                       @RequestParam("getting") Timestamp getting) {
        Flight flight = new Flight(vehicleService.getById(vehicleId), crewService.getById(crewId),
                clientService.getById(clientId), sending, getting);
        flightService.save(flight);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    /**
     * Удаление рейса (Deleting of flight)
     * @param id ID рейса (Flight's id)
     * @return Статус ответа (Response status)
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Flight> delete(@RequestParam("id") Long id) {
        flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
