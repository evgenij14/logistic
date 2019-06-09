package com.task.logistic.controller;

import com.task.logistic.entity.Employee;
import com.task.logistic.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для обслуживания таблицы работников (Controller for the maintenance of the table of employees)
 * Принимает запросы на url : "/employee" (Accepts requests on url : "/employee")
 */
@RestController
@RequestMapping("/employee")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Получение всех работников из БД (Getting all employees from Database)
     *
     * @return Возвращает список работников в формате JSON + статус ответа (Return list of employees on JSON + response status)
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> out = employeeService.getAllEmployees();
        if (out != null) {
            return new ResponseEntity<>(out, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Получение работника по пасспорту (Getting employee by passport)
     *
     * @param passport Пасспорт работника(Passport of employee)
     * @return Возвращает работника в формате JSON + статус ответа (Return employee on JSON + response status)
     */
    @GetMapping("/get-by-passport")
    public ResponseEntity<Employee> getByPassport(@RequestParam("passport") String passport) {
        Employee e = employeeService.getByPassport(passport);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Получение работника по ID (Getting the employee by ID)
     * @param id ID работника (Employee's id)
     * @return Возвращает работника в формате JSON + статус ответа (Return employee on JSON + response status)
     */
    @GetMapping("/get-by-id")
    public ResponseEntity<Employee> getById(@RequestParam("id") Long id) {
        Employee e = employeeService.getById(id);
        if (e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Получение работника по имени и фамилии (Getting the employee by name and surname)
     * @param name Имя работника (The name of employee)
     * @param lastName Фамилия работника (The surname of employee)
     * @return Возвращает работника в формате JSON + статус ответа (Return employee on JSON + response status)
     */
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

    /**
     * Сохранение работника (Save of employee)
     * @param employee Объект работника, сформированный на front-end (The object of employee, which is formed on front-end)
     * @param errors Возможные ошибки, которые могут возникнуть при формировании объекта работника
     *               (Possible errors that may occur during the formation of the employee object)
     * @return Возвращает работника в формате JSON + статус ответа (Return employee on JSON + response status)
     */
    @PostMapping("/save")
    public ResponseEntity<Employee> save(@Valid @RequestBody Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            employeeService.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        }
    }

    /**
     *  Удаление работника (Deleting of employee)
     * @param id ID работника (ID of employee)
     * @return Статус ответа (Response status)
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Employee> delete(@RequestParam("id") Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
