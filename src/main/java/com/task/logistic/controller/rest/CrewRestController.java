package com.task.logistic.controller.rest;

import com.task.logistic.entity.Crew;
import com.task.logistic.entity.Employee;
import com.task.logistic.service.CrewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crew")
public class CrewRestController {
    private final CrewService crewService;

    public CrewRestController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Crew>> getAll() {
        List<Crew> allCrew = crewService.getAllCrew();
        if (!allCrew.isEmpty()) {
            return new ResponseEntity<>(allCrew, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/save")
    public ResponseEntity<List<Employee>> save(@RequestParam("name1") String name1,
                                               @RequestParam("name2") String name2,
                                               @RequestParam("lastName1") String lastName1,
                                               @RequestParam("lastName2") String lastName2) {
        crewService.save(name1, name2, lastName1, lastName2);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Crew> delete(@RequestParam("id") Long id) {
        crewService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
