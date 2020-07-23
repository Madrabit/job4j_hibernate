package ru.job4j.hibernate.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author madrabit on 18.07.2020
 * @version 1$
 * @since 0.1
 */
@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ElementCollection(targetClass=Car.class)
    private Set<Car> carSet = new HashSet<>();

    public static Driver of(String name) {
        Driver driver = new Driver();
        driver.name = name;
        return driver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner",
            joinColumns = {
                    @JoinColumn(name = "driver_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "car_id", nullable = false, updatable = false)
            }
    )
    public Set<Car> getCarSet() {
        return carSet;
    }

    public void setCarSet(Set<Car> carSet) {
        this.carSet = carSet;
    }

    public void addCar(Car car) {
        carSet.add(car);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id &&
                Objects.equals(name, driver.name) &&
                Objects.equals(carSet, driver.carSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, carSet);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
