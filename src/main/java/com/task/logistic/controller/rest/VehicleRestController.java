package com.task.logistic.controller.rest;

import com.task.logistic.entity.Vehicle;
import com.task.logistic.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleRestController {
    private final VehicleService vehicleService;

    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Vehicle> getById(@RequestParam("id") Long id) {
        Vehicle vehicle = vehicleService.getById(id);
        if (vehicle != null) {
            return new ResponseEntity<>(vehicleService.getById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Vehicle>> getAll() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        if (!vehicles.isEmpty()) {
            return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-numberplate")
    public ResponseEntity<Vehicle> getByNumberPlate(@RequestParam("numberplate") String numberplate) {
        Vehicle vehicle = vehicleService.getByNumberPlate(numberplate);
        if (vehicle != null) {
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Vehicle> save(@Valid @RequestBody Vehicle vehicle, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            vehicleService.save(vehicle);
            return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Vehicle> delete(@RequestParam("id") Long id) {
        vehicleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
