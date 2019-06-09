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

    @Transactional
    public Vehicle getById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Transactional
    public Vehicle getByNumberPlate(String numberplate) {
        return vehicleRepository.getByNumberplate(numberplate);
    }

    @Transactional
    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Transactional
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Transactional
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }


}

