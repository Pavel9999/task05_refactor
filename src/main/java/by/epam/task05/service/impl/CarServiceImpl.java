package by.epam.task05.service.impl;

import by.epam.task05.dao.CarDAO;
import by.epam.task05.dao.ContractDAO;
import by.epam.task05.dao.DAOProvider;
import by.epam.task05.dao.UserDAO;
import by.epam.task05.entity.Car;
import by.epam.task05.entity.Contract;
import by.epam.task05.logger.MyLogger;
import by.epam.task05.service.CarService;

import java.util.LinkedList;
import java.util.List;

public class CarServiceImpl implements CarService {

    public List<Car> selectAllCars(){

        List<Car> cars = null;

        DAOProvider daoProvider = DAOProvider.getInstance();
        CarDAO carDAO = daoProvider.getCarDAO();
        try{
            cars = carDAO.selectAllCars();
        }catch (Exception e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }


        return cars;
    }

    public List<Car> selectFreeCars(){

        List<Car> cars = null;

        DAOProvider daoProvider = DAOProvider.getInstance();
        CarDAO carDAO = daoProvider.getCarDAO();

        try{
            cars = carDAO.selectFreeCars();
        }catch (Exception e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }

        return cars;
    }

    public List<Car> selectClientCars(int id){
        List<Car> cars = new LinkedList<>();
        DAOProvider daoProvider = DAOProvider.getInstance();
        CarDAO carDAO = daoProvider.getCarDAO();
        ContractDAO contractDAO = daoProvider.getContractDAO();

        try{
            List<Contract> contracts = contractDAO.selectClientContracts(id);
            for (int i = 0 ; i < contracts.size(); i++)
            {
                Car newCar = carDAO.selectCar(contracts.get(i).getId_car());
                cars.add(newCar);
            }
        }catch (Exception e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }

        return cars;
    }

    public Car selectCar(int car_id){

        Car car = null;

        DAOProvider daoProvider = DAOProvider.getInstance();
        CarDAO carDAO = daoProvider.getCarDAO();
        try{
            car = carDAO.selectCar(car_id);
        }catch (Exception e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }


        return car;
    }



}
