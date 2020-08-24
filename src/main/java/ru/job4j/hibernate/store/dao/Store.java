package ru.job4j.hibernate.store.dao;

import java.util.List;

/**
 * @author madrabit on 22.07.2020
 * @version 1$
 * @since 0.1
 */
public interface Store<T> {
    T create (T model);

    void update(T model);

    void deleteCar(int id);

    void deleteDriver(int id);

    List<T> findAll(Class<T> cl);
}
