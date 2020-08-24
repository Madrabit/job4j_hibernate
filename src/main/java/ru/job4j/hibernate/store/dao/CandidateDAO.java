package ru.job4j.hibernate.store.dao;

import ru.job4j.hibernate.model.Candidate;

import java.util.List;

/**
 * @author madrabit on 24.08.2020
 * @version 1$
 * @since 0.1
 * Выполните запросы выборки всех кандидатов, кандидата по id, кандидата по имени, обновления записи кандидата, удаления записи кандидата по id.
 */
public interface CandidateDAO {
    Candidate create(Candidate model);

    void update(String name, int experience, int salary, int id);

    void delete(int id);

    List<Candidate> findAll();

    Candidate findById(int id);

    Candidate findByName(String name);
}
