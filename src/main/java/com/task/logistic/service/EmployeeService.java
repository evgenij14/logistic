package com.task.logistic.service;

import com.task.logistic.entity.Crew;
import com.task.logistic.entity.Employee;
import com.task.logistic.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public Employee getByNameAndLastName(String name, String lastName) {
        return employeeRepository.findByNameAndLastName(name, lastName);
    }

    @Transactional
    public Employee getByPassport(String passport) {
        return employeeRepository.findByPassport(passport);
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        employeeRepository.delete(getById(id));
    }

    @Transactional
    public List<Employee> getEmployeeInOneCrew(Long id) {
        return employeeRepository.getEmployeeOfCrew(id);
    }

    @Transactional
    public void save(Employee e) {
        employeeRepository.save(e);
    }

    @Transactional
    public void update(String name, String lastname, Crew crew) {
        employeeRepository.update(crew.getId(), name, lastname);
    }

    @Transactional
    public void deletingCrew(String name, String lastName) {
        employeeRepository.deletingCrew(name, lastName);
    }
}
