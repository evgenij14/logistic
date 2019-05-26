package entity;

//import javax.persistence.*;
import java.util.Objects;
//@Entity
//@Table(name = "")
public class Crew {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Employee e1;
    private Employee e2;

    public Crew() {
    }

    public Crew(int id, Employee e1, Employee e2) {
        this.id = id;
        this.e1 = e1;
        this.e2 = e2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getE1() {
        return e1;
    }

    public void setE1(Employee e1) {
        this.e1 = e1;
    }

    public Employee getE2() {
        return e2;
    }

    public void setE2(Employee e2) {
        this.e2 = e2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crew crew = (Crew) o;
        return id == crew.id &&
                Objects.equals(e1, crew.e1) &&
                Objects.equals(e2, crew.e2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, e1, e2);
    }

    @Override
    public String toString() {
        return "Crew{" +
                "id=" + id +
                ", e1=" + e1 +
                ", e2=" + e2 +
                '}';
    }
}
