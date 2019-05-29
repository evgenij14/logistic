package com.task.logistic.service;

import com.task.logistic.repository.CrewRepository;
import com.task.logistic.repository.EmployeeRepository;
import entity.Crew;
import entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee getById(Long id) {
        return employeeRepository.getOne(id);
    }

    public Employee getByNameAndLastName(String name, String lastName) {
        return employeeRepository.findByNameAndLastName(name, lastName);
    }

    public Employee getByPassport(String passport) {
        return employeeRepository.findByPassport(passport);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public void save(String passport, String name, String lastName, int age, String address, int salary) {
        LocalDate date = LocalDate.now();
        employeeRepository.save(new Employee(passport, name, lastName, age, address, date, salary, null));
    }

    public void delete(Long id) {
        employeeRepository.delete(getById(id));
    }
}
