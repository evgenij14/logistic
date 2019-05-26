package entity;

//import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
//@Entity
//@Table(name = "")
public class Employee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String passport;
    private int age;
    private String address;
    private LocalDate placement;
    private int salary;

    public Employee() {
    }

    public Employee(int id, String passport, int age, String address, LocalDate placement, int salary) {
        this.id = id;
        this.passport = passport;
        this.age = age;
        this.address = address;
        this.placement = placement;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                age == employee.age &&
                salary == employee.salary &&
                Objects.equals(passport, employee.passport) &&
                Objects.equals(address, employee.address) &&
                Objects.equals(placement, employee.placement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passport, age, address, placement, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", passport='" + passport + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", placement=" + placement +
                ", salary=" + salary +
                '}';
    }
}
