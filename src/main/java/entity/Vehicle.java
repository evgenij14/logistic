package entity;

//import javax.persistence.*;
import java.util.Objects;
//@Entity
//@Table(name = "")
public class Vehicle {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int maxSpeed;
    private String numberplate;
    private int carrying;
    private int gasMileage;

    public Vehicle() {
    }

    public Vehicle(int id, int maxSpeed, String numberplate, int carrying, int gasMileage) {
        this.id = id;
        this.maxSpeed = maxSpeed;
        this.numberplate = numberplate;
        this.carrying = carrying;
        this.gasMileage = gasMileage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id &&
                maxSpeed == vehicle.maxSpeed &&
                carrying == vehicle.carrying &&
                gasMileage == vehicle.gasMileage &&
                Objects.equals(numberplate, vehicle.numberplate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maxSpeed, numberplate, carrying, gasMileage);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", maxSpeed=" + maxSpeed +
                ", numberplate='" + numberplate + '\'' +
                ", carrying=" + carrying +
                ", gasMileage=" + gasMileage +
                '}';
    }
}
