package com.task.logistic.service;

import com.task.logistic.entity.Vehicle;
import com.task.logistic.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Поиск TC по id (Searching the vehicle by id)
     *
     * @param id ID ТС (Vehicle's ID)
     * @return Объект ТС(Vehicle's object)
     */
    @Transactional
    public Vehicle getById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    /**
     * Поиск ТС по номерному знаку (Searching vehicle by numberplate)
     *
     * @param numberplate Номерной знак (Numberplate)
     * @return Возвращает объект ТС(Return the vehicle)
     */
    @Transactional
    public Vehicle getByNumberPlate(String numberplate) {
        return vehicleRepository.getByNumberplate(numberplate);
    }

    /**
     * Удаление ТС из БД (Deleting the vehicle from Database)
     * @param id ID ТС (Vehicle's ID)
     */
    @Transactional
    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }

    /**
     * Получение всех ТС из БД (Getting all vehicles from Database)
     * @return Возвращает список ТС (Return the list of vehicles)
     */
    @Transactional
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    /**
     * Сохранение ТС в БД (Saving vehicle in Database)
     * @param vehicle Объект TC(The object of vehicle)
     */
    @Transactional
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }


}

