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

    private int experience;

    private int salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public static Candidate of (String name, int expirience, int salary, Account account) {
        Candidate candidate = new Candidate();
        candidate.name = name;
        candidate.experience = expirience;
        candidate.salary = salary;
        candidate.account = account;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int expirience) {
        this.experience = expirience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return id == candidate.id &&
                experience == candidate.experience &&
                salary == candidate.salary &&
                Objects.equals(name, candidate.name) &&
                Objects.equals(account, candidate.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience, salary, account);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expirience=" + experience +
                ", salary=" + salary +
                ", account=" + account +
                '}';
    }
}
