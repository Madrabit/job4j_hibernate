package ru.job4j.hibernate.store.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.hibernate.model.car.Driver;
import ru.job4j.hibernate.model.car.Engine;
import ru.job4j.hibernate.model.car.Car;
import ru.job4j.hibernate.store.dao.Store;
import ru.job4j.hibernate.util.HibernateUtil;

import java.util.List;
import java.util.function.Function;

/**
 * @author madrabit on 22.07.2020
 * @version $
 * @since 0.1
 */
public class CarStore<T> implements Store<T> {
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
    public T create(T model) {
        return this.tx(session -> {
            session.save(model);
            return model;
        });
    }

    @Override
    public void update(T model) {
        this.tx(session -> {
            session.update(model);
            return null;
        });
    }

    @Override
    public void deleteCar(int id) {
        this.tx(session -> {
            Car car = Car.of("", new Engine());
            car.setId(id);
            session.delete(car);
            return null;
        });
    }

    @Override
    public void deleteDriver(int id) {
        this.tx(session -> {
            Driver driver = Driver.of("");
            driver.setId(id);
            session.delete(driver);
            return null;
        });
    }

    @Override
    public List<T> findAll(Class<T> cl) {
        return this.tx(session -> session.createQuery("from " + cl.getName(), cl).list());
    }

}
