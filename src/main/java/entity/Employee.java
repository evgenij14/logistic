package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "passport", unique = true)
    private String passport;

    @NotNull
    @Size(max = 64)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(max = 64)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "age")
    private int age;

    @Size(max = 128)
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "placement")
    private LocalDate placement;

    @NotNull
    @Column(name = "salary")
    private int salary;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "crew_id")
    private Crew crew;

    public Employee() {
    }

    public Employee(@NotNull String passport, @NotNull String name, @NotNull String lastName, @NotNull int age, String address, @NotNull LocalDate placement, @NotNull int salary, Crew crew) {
        this.passport = passport;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.placement = placement;
        this.salary = salary;
        this.crew = crew;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getPlacement() {
        return placement;
    }

    public void setPlacement(LocalDate placement) {
        this.placement = placement;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Crew getCrew() {
        return crew;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                salary == employee.salary &&
                Objects.equals(id, employee.id) &&
                Objects.equals(passport, employee.passport) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(address, employee.address) &&
                Objects.equals(placement, employee.placement) &&
                Objects.equals(crew, employee.crew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passport, name, lastName, age, address, placement, salary, crew);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", passport='" + passport + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", placement=" + placement +
                ", salary=" + salary +
                ", crew=" + crew +
                '}';
    }
}
