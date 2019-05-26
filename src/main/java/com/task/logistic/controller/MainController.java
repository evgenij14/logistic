package com.task.logistic.controller;

import entity.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Employee emp(@RequestParam(value = "name", defaultValue = "noname") String name) {
        return new Employee(13, "VT140737", 22, name, null, 35000);
    }
}
