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

    /**
     * Получение работника по ID (Getting the employee by ID)
     *
     * @param id ID работника (Employee's id)
     * @return Возвращает объект работника(Return employee)
     */
    @Transactional
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    /**
     * Получение работника по имени и фамилии (Getting the employee by name and surname)
     *
     * @param name     Имя работника (The name of employee)
     * @param lastName Фамилия работника (The surname of employee)
     * @return Возвращает объект работника(Return employee)
     */
    @Transactional
    public Employee getByNameAndLastName(String name, String lastName) {
        return employeeRepository.findByNameAndLastName(name, lastName);
    }

    /**
     * Получение работника по пасспорту (Getting employee by passport)
     * @param passport Пасспорт работника(Passport of employee)
     * @return Возвращает объекта работника (Return employee)
     */
    @Transactional
    public Employee getByPassport(String passport) {
        return employeeRepository.findByPassport(passport);
    }

    /**
     * Получение всех работников из БД (Getting all employees from Database)
     * @return Возвращает список работников(Return list of employees)
     */
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     *  Удаление работника их БД (Deleting of employee from Database)
     * @param id ID работника (ID of employee)
     */
    @Transactional
    public void delete(Long id) {
        employeeRepository.delete(getById(id));
    }

    /**
     * Получение работников одного экипажа (Getting employees from one crew)
     * @param id ID экипажа (Crew's id)
     * @return Возвращает список работников(Return List of employees)
     */
    @Transactional
    public List<Employee> getEmployeeInOneCrew(Long id) {
        return employeeRepository.getEmployeeOfCrew(id);
    }

    /**
     * Сохранение работника (Save of employee)
     * @param employee Объект работника(The object of employee)
     */
    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    /**
     * Добавление работника в экипаж (Connected the employee with crew)
     * @param name Имя работника (Employee's name)
     * @param lastname Фамилия работника (Employee's surname)
     * @param crew Объект экипажа (The object of crew)
     */
    @Transactional
    public void update(String name, String lastname, Crew crew) {
        employeeRepository.update(crew.getId(), name, lastname);
    }

    /**
     * Удаление поля экипажа у работника (Deleting crew field of employee)
     * @param name Имя работника (Employee's name)
     * @param lastName Фамилия работника (Employee's lastname)
     */
    @Transactional
    public void deletingCrew(String name, String lastName) {
        employeeRepository.deletingCrew(name, lastName);
    }
}
