package com.task.logistic.controller.rest;

import com.task.logistic.entity.Employee;
import com.task.logistic.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> out = employeeService.getAllEmployees();
        if (out != null) {
            return new ResponseEntity<>(out, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/get-by-passport")
    public ResponseEntity<Employee> getByPassport(@RequestParam("passport") String passport) {
        Employee e = employeeService.getByPassport(passport);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Employee> getById(@RequestParam("id") Long id) {
        Employee e = employeeService.getById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-name-and-lastname")
    public ResponseEntity<Employee> getByNameAndLastName(@RequestParam("name") String name,
                                                         @RequestParam("lastname") String lastName) {
        Employee e = employeeService.getByNameAndLastName(name, lastName);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> save(@Valid @RequestBody Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            employeeService.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Employee> delete(@RequestParam("id") Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
