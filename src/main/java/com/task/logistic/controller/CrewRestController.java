package com.task.logistic.controller;

import com.task.logistic.entity.Crew;
import com.task.logistic.entity.Employee;
import com.task.logistic.service.CrewService;
import com.task.logistic.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для обслуживания таблицы с экипажами (Controller for the maintenance of the table of crews)
 * Принимает запросы на url : "/crew" (Accepts requests on url : "/crew")
 */
@RestController
@RequestMapping("/crew")
public class CrewRestController {
    private final EmployeeService employeeService;
    private final CrewService crewService;

    public CrewRestController(CrewService crewService, EmployeeService employeeService) {
        this.crewService = crewService;
        this.employeeService = employeeService;
    }

    /**
     * Получение всех экипажей из БД (Getting all crews from Database)
     *
     * @return Возвращает список экипажей в формате JSON + статус ответа (Return List of crews on JSON + response status)
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Crew>> getAll() {
        List<Crew> allCrew = crewService.getAllCrew();
        if (!allCrew.isEmpty()) {
            return new ResponseEntity<>(allCrew, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Создает и сохраняет в БД новый экипаж и связывает с необходимыми работниками из таблицы работников
     * (Create and save in Database new crew and connect with necessary employees from table employee)
     *
     * @param name1     Имя первого работника (The name of first employee)
     * @param name2     Имя второго работника (The name of second employee)
     * @param lastName1 Фамилия первого работника (The surname of first employee)
     * @param lastName2 Фамилия второго работника (The surname of second employee)
     * @return Возвращает статус ответа
     * (Return response status)
     */
    @PostMapping("/save")
    public ResponseEntity<List<Employee>> save(@RequestParam("name1") String name1,
                                               @RequestParam("name2") String name2,
                                               @RequestParam("lastName1") String lastName1,
                                               @RequestParam("lastName2") String lastName2) {
        crewService.save(name1, name2, lastName1, lastName2);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Удаляет экипаж из БД (Delete crew from Database)
     * @param id ID экипажа (Crew's ID)
     * @return Возвращает статус ответа (Return response status)
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Crew> delete(@RequestParam("id") Long id) {
        crewService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Получение работников одного экипажа (Getting employees from one crew)
     *
     * @param id ID экипажа (Crew's id)
     * @return Возвращает список работников в формате JSON + статус ответа(Return List of employees in JSON + response status)
     */
    @GetMapping("/get-employees-of-crew")
    public ResponseEntity<List<Employee>> getEmployeesOfOneCrew(@RequestParam("id") Long id) {
        List<Employee> out = employeeService.getEmployeeInOneCrew(id);
        if (!out.isEmpty()) {
            return new ResponseEntity<>(out, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
