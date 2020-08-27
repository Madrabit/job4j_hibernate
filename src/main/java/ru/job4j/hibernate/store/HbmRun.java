package ru.job4j.hibernate.store;

import ru.job4j.hibernate.model.car.Car;
import ru.job4j.hibernate.model.car.Driver;
import ru.job4j.hibernate.model.car.Engine;
import ru.job4j.hibernate.store.dao.impl.CarStore;

import java.util.List;

/**
 * @author madrabit on 07.07.2020
 * @version 1$
 * @since 0.1
 * Class for Testing many-to-many in Hibernate.
 */
public class HbmRun {
    public static void main(String[] args) {
        try {
            CarStore carsStore = new CarStore();
            Engine engine = Engine.of("V8");
            Car car = Car.of("bmw", engine);
            Driver driver = Driver.of("Max");
            carsStore.create(car);
            carsStore.create(driver);
            List<Car> cars = carsStore.findAll(Car.class);
            cars.stream().map(Car::getName).forEach(System.out::println);
            car.setName("audi");
            carsStore.update(car);
            cars = carsStore.findAll(Car.class);
            cars.stream().map(Car::getName).forEach(System.out::println);
            List<Driver> drivers = carsStore.findAll(Driver.class);
            drivers.stream().map(Driver::getName).forEach(System.out::println);
            carsStore.deleteDriver(driver.getId());
            drivers = carsStore.findAll(Driver.class);
            drivers.stream().map(Driver::getName).forEach(System.out::println);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
