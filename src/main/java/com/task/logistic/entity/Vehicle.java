package com.task.logistic.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "vehicle_id")
    private Long id;

    @NotNull
    @Column(name = "max_speed")
    private int maxSpeed;

    @NotNull
    @Size(max = 16)
    @Column(name = "numberplate", unique = true)
    private String numberplate;

    @NotNull
    @Column(name = "carrying")
    private int carrying;

    @Column(name = "gas_mileage")
    private int gasMileage;

    @OneToOne(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Flight flight;

    public Vehicle() {
    }

    public Vehicle(int maxSpeed, String numberplate, int carrying, int gasMileage) {
        this.maxSpeed = maxSpeed;
        this.numberplate = numberplate;
        this.carrying = carrying;
        this.gasMileage = gasMileage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }

    public int getCarrying() {
        return carrying;
    }

    public void setCarrying(int carrying) {
        this.carrying = carrying;
    }

    public int getGasMileage() {
        return gasMileage;
    }

    public void setGasMileage(int gasMileage) {
        this.gasMileage = gasMileage;
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
        Vehicle vehicle = (Vehicle) o;
        return maxSpeed == vehicle.maxSpeed &&
                carrying == vehicle.carrying &&
                gasMileage == vehicle.gasMileage &&
                Objects.equals(id, vehicle.id) &&
                Objects.equals(numberplate, vehicle.numberplate) &&
                Objects.equals(flight, vehicle.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maxSpeed, numberplate, carrying, gasMileage, flight);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", maxSpeed=" + maxSpeed +
                ", numberplate='" + numberplate + '\'' +
                ", carrying=" + carrying +
                ", gasMileage=" + gasMileage +
                ", flight=" + flight +
                '}';
    }
}
