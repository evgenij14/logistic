package com.task.logistic;

import com.task.logistic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.task.logistic.*")
public class LogisticApplication implements CommandLineRunner {
    private final CrewService crewService;
    private final ClientService clientService;
    private final FlightService flightService;
    private final EmployeeService employeeService;
    private final VehicleService vehicleService;

    public LogisticApplication(VehicleService vehicleService, EmployeeService employeeService, FlightService flightService, ClientService clientService, CrewService crewService) {
        this.vehicleService = vehicleService;
        this.employeeService = employeeService;
        this.flightService = flightService;
        this.clientService = clientService;
        this.crewService = crewService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LogisticApplication.class, args);
    }

    @Override
    public void run(String... args) {
//    clientService.save(new Client("Kiska", "Kisya", "d44k74", 5626));
//    employeeService.save(new Employee("VT140735", "Dmitrij", "Gubochkin", 20, "d44k74", LocalDate.now(), 5260, null));
//    employeeService.save(new Employee("VK140736", "Alina", "Kulyna", 20, "d44k74", LocalDate.now(), 5317, null));
//        vehicleService.save(new Vehicle(140, "AA4701AI", 2000, 40));
//        flightService.save(new Flight(vehicleService.getById(4L), crewService.getById(1L), clientService.getById(6L), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now().plusHours(10))));
//        clientService.delete(1L);
//        crewService.save("Alina", "Dmitrij", "Kulyna", "Gubochkin");
//        System.out.println(flightService.getByClientInfo("Kiska", "Kisya"));
    }
}
