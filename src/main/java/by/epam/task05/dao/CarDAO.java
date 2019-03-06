package by.epam.task05.dao;


import by.epam.task05.entity.Car;

import java.util.List;

public interface CarDAO {

    List<Car> selectAllCars() throws DaoException ;
    List<Car> selectFreeCars() throws DaoException ;
    Car selectCar(int car_id) throws DaoException ;

    void setContractID(int idCar, int idContract) throws DaoException;


}




