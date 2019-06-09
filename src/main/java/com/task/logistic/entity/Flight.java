package com.task.logistic.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "flight")
public class Flight implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @OneToOne()
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @NotNull
    @OneToOne()
    @JoinColumn(name = "crew_id")
    private Crew crew;

    @NotNull
    @OneToOne()
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
    @Column(name = "sending")
    private Timestamp sending;

    @NotNull
    @Column(name = "getting")
    private Timestamp getting;

    public Flight() {
    }

    public Flight(Vehicle vehicle, Crew crew, Client client, Timestamp sending, Timestamp getting) {
        this.vehicle = vehicle;
        this.crew = crew;
        this.client = client;
        this.sending = sending;
        this.getting = getting;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Timestamp getSending() {
        return sending;
    }

    public void setSending(Timestamp sending) {
        this.sending = sending;
    }

    public Timestamp getGetting() {
        return getting;
    }

    public void setGetting(Timestamp getting) {
        this.getting = getting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) &&
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
                '}';
    }
}
