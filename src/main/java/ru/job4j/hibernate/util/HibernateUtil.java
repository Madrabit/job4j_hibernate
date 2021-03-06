package ru.job4j.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import ru.job4j.hibernate.model.Account;
import ru.job4j.hibernate.model.Candidate;
import ru.job4j.hibernate.model.Vacancy;
import ru.job4j.hibernate.model.car.Car;
import ru.job4j.hibernate.model.car.Driver;
import ru.job4j.hibernate.model.car.Engine;

import java.util.Properties;

/**
 * @author madrabit on 25.06.2020
 * @version 1$
 * @since 0.1
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://127.0.0.1:5432/job4j_hibernate");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "password");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(Driver.class);
                configuration.addAnnotatedClass(Engine.class);
                configuration.addAnnotatedClass(Candidate.class);
                configuration.addAnnotatedClass(Account.class);
                configuration.addAnnotatedClass(Vacancy.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
