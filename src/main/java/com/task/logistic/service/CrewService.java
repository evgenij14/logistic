package com.task.logistic.service;

import com.task.logistic.entity.Crew;
import com.task.logistic.entity.Employee;
import com.task.logistic.repository.CrewRepository;
import com.task.logistic.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CrewService {
    private final EmployeeRepository employeeRepository;
    private final CrewRepository crewRepository;


    public CrewService(CrewRepository crewRepository, EmployeeRepository employeeRepository) {
        this.crewRepository = crewRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public List<Crew> getAllCrew() {
        return crewRepository.findAll();
    }

    @Transactional
    public Crew getById(Long id) {
        return crewRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        for (Employee e : employeeRepository.getEmployeeOfCrew(id)) {
            employeeRepository.deletingCrew(e.getName(), e.getLastName());
        }
        crewRepository.deleteById(id);
    }

    @Transactional
    public void save(String name1, String name2, String lastName1, String lastName2) {
        Crew crew = new Crew();
        crewRepository.save(crew);
        employeeRepository.update(crew.getId(), name1, lastName1);
        employeeRepository.update(crew.getId(), name2, lastName2);
    }
}
