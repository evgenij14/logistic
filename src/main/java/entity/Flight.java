package entity;

//import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
//@Entity
//@Table
public class Flight {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Vehicle vehicle;
    private Crew crew;
    private Client client;
    private LocalDate sending;
    private LocalDate getting;

    public Flight() {
    }

    public Flight(int id, Vehicle vehicle, Crew crew, Client client, LocalDate sending, LocalDate getting) {
        this.id = id;
        this.vehicle = vehicle;
        this.crew = crew;
        this.client = client;
        this.sending = sending;
        this.getting = getting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getSending() {
        return sending;
    }

    public void setSending(LocalDate sending) {
        this.sending = sending;
    }

    public LocalDate getGetting() {
        return getting;
    }

    public void setGetting(LocalDate getting) {
        this.getting = getting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id &&
                Objects.equals(vehicle, flight.vehicle) &&
                Objects.equals(crew, flight.crew) &&
                Objects.equals(client, flight.client) &&
                Objects.equals(sending, flight.sending) &&
                Objects.equals(getting, flight.getting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vehicle, crew, client, sending, getting);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", vehicle=" + vehicle +
                ", crew=" + crew +
                ", client=" + client +
                ", sending=" + sending +
                ", getting=" + getting +
                ", status=" +
                '}';
    }
}
