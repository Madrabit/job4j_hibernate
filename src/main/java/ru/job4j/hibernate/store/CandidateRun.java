package ru.job4j.hibernate.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.hibernate.model.Account;
import ru.job4j.hibernate.model.Candidate;
import ru.job4j.hibernate.model.Vacancy;
import ru.job4j.hibernate.store.dao.JobDao;
import ru.job4j.hibernate.store.dao.impl.CandidateDaoImpl;

/**
 * @author madrabit on 24.08.2020
 * @version 1$
 * @since 0.1
 */
public class CandidateRun {
    private static final Logger LOG = LogManager.getLogger(CandidateRun.class.getName());

    public static void main(String[] args) {
        try {
            JobDao dao = new CandidateDaoImpl();
            Vacancy vacancy1 = Vacancy.of("Proger", 1000);
            Vacancy vacancy2 = Vacancy.of("PizzaMaker", 10);
            Account accMax =  Account.of("Max");
            Account accPetr =  Account.of("Petr");
            Account accAlex =  Account.of("Alex");
            accMax.addVacancy(vacancy1);
            accPetr.addVacancy(vacancy2);
            Candidate max = Candidate.of("Max", 6, 2000, accMax);
            Candidate petr = Candidate.of("Petr", 3, 1000, accPetr);
            Candidate alex = Candidate.of("Alex", 9, 3000, accAlex);
            dao.create(accMax);
            dao.create(accPetr);
            dao.create(accAlex);
            dao.create(max);
            dao.create(petr);
            dao.create(alex);
            System.out.println(dao.findFullById(1));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
