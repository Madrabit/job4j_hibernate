package ru.job4j.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author madrabit on 24.08.2020
 * @version 1$
 * @since 0.1
 */

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int expirience;

    private int salary;

    public static Candidate of (String name, int expirience, int salary) {
        Candidate candidate = new Candidate();
        candidate.name = name;
        candidate.expirience = expirience;
        candidate.salary = salary;
        return candidate;
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

    public int getExpirience() {
        return expirience;
    }

    public void setExpirience(int expirience) {
        this.expirience = expirience;
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
        Candidate candidate = (Candidate) o;
        return id == candidate.id &&
                expirience == candidate.expirience &&
                salary == candidate.salary &&
                Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expirience, salary);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expirience=" + expirience +
                ", salary=" + salary +
                '}';
    }
}
