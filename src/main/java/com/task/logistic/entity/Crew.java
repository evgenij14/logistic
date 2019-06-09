package com.task.logistic.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "crew")
public class Crew implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "crew_id")
    private Long id;

    @OneToOne(mappedBy = "crew", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Flight flight;

    @OneToMany(mappedBy = "crew", fetch = FetchType.LAZY)
    private Set<Employee> employees;


    public Crew() {
    }

    public Crew(Employee... employees) {
        this.employees = Stream.of(employees).collect(Collectors.toSet());
        this.employees.forEach(x -> x.setCrew(this));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crew crew = (Crew) o;
        return Objects.equals(id, crew.id) &&
                Objects.equals(employees, crew.employees) &&
                Objects.equals(flight, crew.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employees, flight);
    }

    @Override
    public String toString() {
        return "Crew{" +
                "id=" + id +
                ", flight=" + flight +
                '}';
    }
}
