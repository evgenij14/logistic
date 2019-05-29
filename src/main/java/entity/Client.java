package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 64)
    @Column(name = "name")
    private String name;


    @NotNull
    @Size(max = 64)
    @Column(name = "last_name")
    private String lastname;

    @Size(max = 128)
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "payment")
    private int payment;

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Flight flight;

    public Client() {
    }

    public Client(String name, String lastname, String address, int payment, Flight flight) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.payment = payment;
        this.flight = flight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
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
        Client client = (Client) o;
        return payment == client.payment &&
                Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(lastname, client.lastname) &&
                Objects.equals(address, client.address) &&
                Objects.equals(flight, client.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, address, payment, flight);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", payment=" + payment +
                ", flight=" + flight +
                '}';
    }
}
