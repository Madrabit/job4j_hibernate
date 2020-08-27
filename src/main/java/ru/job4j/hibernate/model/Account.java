package ru.job4j.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author madrabit on 26.08.2020
 * @version 1$
 * @since 0.1
 */
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private Candidate candidate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancyList = new ArrayList<>();

    public void addVacancy(Vacancy vacancy) {
        this.vacancyList.add(vacancy);
    }

    public static Account of(String userName) {
        Account account = new Account();
        account.userName = userName;
        account.active = true;
        return account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }

    public void setVacancyList(List<Vacancy> vacancyList) {
        this.vacancyList = vacancyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                active == account.active &&
                Objects.equals(userName, account.userName) &&
                Objects.equals(vacancyList, account.vacancyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, active, vacancyList);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", active=" + active +
                ", vacancyList=" + vacancyList +
                '}';
    }
}
