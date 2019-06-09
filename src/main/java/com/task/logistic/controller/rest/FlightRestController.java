package com.task.logistic.controller.rest;

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

    @GetMapping("/get-all")
    public ResponseEntity<List<Flight>> getAll() {
        List<Flight> flights = flightService.getAllFlights();
        if (!flights.isEmpty()) {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Flight> getById(@RequestParam("id") Long id) {
        Flight flight = flightService.getById(id);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-client-id")
    public ResponseEntity<Flight> getByClientId(@RequestParam("client_id") Long id) {
        Flight flight = flightService.getFlightByClientId(id);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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

    @DeleteMapping("/delete")
    public ResponseEntity<Flight> delete(@RequestParam("id") Long id) {
        flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
