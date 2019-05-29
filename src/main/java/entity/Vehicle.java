package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "max_speed")
    private int maxSpeed;

    @Column(name = "numberplate", unique = true)
    private String numberplate;

    @Column(name = "carrying")
    private int carrying;

    @Column(name = "gas_mileage")
    private int gasMileage;

    @OneToOne(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Flight flight;

    public Vehicle() {
    }

    public Vehicle(int maxSpeed, String numberplate, int carrying, int gasMileage, Flight flight) {
        this.maxSpeed = maxSpeed;
        this.numberplate = numberplate;
        this.carrying = carrying;
        this.gasMileage = gasMileage;
        this.flight = flight;
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
