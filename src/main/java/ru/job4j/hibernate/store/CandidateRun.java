package ru.job4j.hibernate.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.hibernate.model.Candidate;
import ru.job4j.hibernate.store.dao.CandidateDAO;
import ru.job4j.hibernate.store.dao.impl.CandidateDaoImpl;
import ru.job4j.hibernate.store.dao.impl.CarStore;

import java.util.List;

/**
 * @author madrabit on 24.08.2020
 * @version 1$
 * @since 0.1
 */
public class CandidateRun {
    private static final Logger LOG = LogManager.getLogger(CandidateRun.class.getName());

    public static void main(String[] args) {
        try {
            CandidateDAO dao = new CandidateDaoImpl();
            Candidate max = Candidate.of("Max", 6, 2000);
            Candidate petr = Candidate.of("Petr", 3, 1000);
            Candidate alex = Candidate.of("Alex", 9, 3000);
            dao.create(max);
            dao.create(petr);
            dao.create(alex);
            System.out.println(dao.findById(1).getName());
            System.out.println(dao.findByName("Alex").getId());
            dao.delete(2);
            dao.update("Mad Max", 7, 3000, 1);
            System.out.println(dao.findById(1).getName());
            List<Candidate> list = dao.findAll();
            list.stream().map(Candidate::getName).forEach(System.out::println);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
