package com.task.logistic.controller;

import com.task.logistic.entity.Vehicle;
import com.task.logistic.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для обслуживания таблицы транспортных средств (The controller for the maintenance of the vehicle table)
 * Принимает запросы на url : "/vehicle" (Accepts requests on url : "/vehicle")
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleRestController {
    private final VehicleService vehicleService;

    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Поиск TC по id (Searching the vehicle by id)
     *
     * @param id ID ТС (Vehicle's ID)
     * @return Объект ТС в формате JSON + статус ответа (Vehicle's object on JSON + response status)
     */
    @GetMapping("/get-by-id")
    public ResponseEntity<Vehicle> getById(@RequestParam("id") Long id) {
        Vehicle vehicle = vehicleService.getById(id);
        if (vehicle != null) {
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Получение всех ТС из БД (Getting all vehicles from Database)
     *
     * @return Возвращает список ТС в формате JSON + статус ответа (Return the list of vehicles on JSON + response status)
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Vehicle>> getAll() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        if (!vehicles.isEmpty()) {
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Поиск ТС по номерному знаку (Searching vehicle by numberplate)
     * @param numberplate Номерной знак (Numberplate)
     * @return Возвращает объект ТС в формате JSON + статус ответа(Return the object of vehicle on JSON + response status)
     */
    @GetMapping("/get-by-numberplate")
    public ResponseEntity<Vehicle> getByNumberPlate(@RequestParam("numberplate") String numberplate) {
        Vehicle vehicle = vehicleService.getByNumberPlate(numberplate);
        if (vehicle != null) {
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Сохранение ТС в БД (Saving vehicle in Database)
     * @param vehicle Объект TC, сформированный на front-end (The object of vehicle, which is formed on front-end)
     * @param errors Возможные ошибки, которые могут возникнуть при формировании объекта TC
     *               (Possible errors that may occur during the formation of the vehicle object)
     * @return Возвращает объект сохраненного клиента в формате JSON + статус ответа (Return saved vehicle on JSON + response status)
     */
    @PostMapping("/save")
    public ResponseEntity<Vehicle> save(@Valid @RequestBody Vehicle vehicle, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            vehicleService.save(vehicle);
            return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
        }
    }

    /**
     * Удаление ТС из БД (Deleting the vehicle from Database)
     * @param id ID ТС (Vehicle's ID)
     * @return Статус ответа (Response status)
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Vehicle> delete(@RequestParam("id") Long id) {
        vehicleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
