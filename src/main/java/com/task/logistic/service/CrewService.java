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

    /**
     * Получение всех экипажей из БД (Getting all crews from Database)
     *
     * @return Возвращает список экипажей(Return List of crews)
     */
    @Transactional
    public List<Crew> getAllCrew() {
        return crewRepository.findAll();
    }

    /**
     * Получение экипажа по ID (Getting the crew by ID)
     *
     * @param id ID экипажа (Crew's ID)
     * @return Объект экипажа (The object of crew)
     */
    @Transactional
    public Crew getById(Long id) {
        return crewRepository.findById(id).orElse(null);
    }

    /**
     * Удаляет экипаж из БД (Delete crew from Database)
     * @param id ID экипажа (Crew's ID)
     */
    @Transactional
    public void delete(Long id) {
        for (Employee e : employeeRepository.getEmployeeOfCrew(id)) {
            employeeRepository.deletingCrew(e.getName(), e.getLastName());
        }
        crewRepository.deleteById(id);
    }

    /**
     * Создает и сохраняет в БД новый экипаж и связывает с необходимыми работниками из таблицы работников
     * (Create and save in Database new crew and connect with necessary employees from table employee)
     * @param name1 Имя первого работника (The name of first employee)
     * @param name2 Имя второго работника (The name of second employee)
     * @param lastName1 Фамилия первого работника (The surname of first employee)
     * @param lastName2 Фамилия второго работника (The surname of second employee)
     */
    @Transactional
    public void save(String name1, String name2, String lastName1, String lastName2) {
        Crew crew = new Crew();
        crewRepository.save(crew);
        employeeRepository.update(crew.getId(), name1, lastName1);
        employeeRepository.update(crew.getId(), name2, lastName2);
    }
}
