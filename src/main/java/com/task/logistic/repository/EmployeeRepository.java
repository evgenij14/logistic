package com.task.logistic.repository;

import entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByNameAndLastName(String name, String lastName);

    Employee findByPassport(String passport);
}
