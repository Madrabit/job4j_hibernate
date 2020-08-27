package ru.job4j.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author madrabit on 26.08.2020
 * @version 1$
 * @since 0.1
 */
@Entity
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;

    private int rate;

    public static Vacancy of(String name, int rate) {
        Vacancy vacancy = new Vacancy();
        vacancy.name = name;
        vacancy.rate = rate;
        return vacancy;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Id == vacancy.Id &&
                rate == vacancy.rate &&
                Objects.equals(name, vacancy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, rate);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
