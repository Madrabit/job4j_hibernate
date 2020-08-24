package ru.job4j.hibernate.store.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.hibernate.model.Candidate;
import ru.job4j.hibernate.store.dao.CandidateDAO;
import ru.job4j.hibernate.util.HibernateUtil;

import java.util.List;
import java.util.function.Function;

/**
 * @author madrabit on 24.08.2020
 * @version 1$
 * @since 0.1
 */
public class CandidateDaoImpl implements CandidateDAO {
    private static final Logger LOG = LogManager.getLogger(CarStore.class.getName());

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    /**
     * Wrapper. Open session -> execute Function -> commit
     *
     * @param command Function for execution: create, update, delete, etc.
     * @return Return query result.
     */
    protected <T> T tx(final Function<Session, T> command) {
        T rsl = null;
        Transaction tx = null;
        try (final Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public Candidate create(Candidate model) {
        return this.tx(session -> {
            session.save(model);
            return model;
        });
    }

    @Override
    public void update(String name, int experience, int salary, int id) {
        this.tx(session -> {
            session.createQuery("update Candidate c set c.name = :newName, c.expirience = :newExp, c.salary = :newSalary " +
                    "where c.id = :fId")
                    .setParameter("newName", name)
                    .setParameter("newExp", experience)
                    .setParameter("newSalary", salary)
                    .setParameter("fId", id)
                    .executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(int id) {
        this.tx(session -> {
            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            return null;
        });

    }

    @Override
    public List<Candidate> findAll() {
        return (List<Candidate>) this.tx(session -> {
            Query query = session.createQuery("from Candidate");
            return query.getResultList();
        });
    }

    @Override
    public Candidate findById(int id) {
        return (Candidate) this.tx(session -> {
            Query query = session.createQuery("from Candidate c where c.id = :fId");
            query.setParameter("fId", id);
            return query.uniqueResult();
        });
    }

    @Override
    public Candidate findByName(String name) {
        return (Candidate) this.tx(session -> {
            Query query = session.createQuery("from Candidate c where c.name = :fName");
            query.setParameter("fName", name);
            return query.uniqueResult();
        });
    }
}
