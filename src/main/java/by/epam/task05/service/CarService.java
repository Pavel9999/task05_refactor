package by.epam.task05.service;

import by.epam.task05.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> selectAllCars();
    List<Car> selectFreeCars();
    List<Car> selectClientCars(int id);
    Car selectCar(int car_id);
}
