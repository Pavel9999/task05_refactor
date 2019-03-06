package by.epam.task05.service;

import by.epam.task05.dao.DaoException;
import by.epam.task05.entity.ContractData;

import java.util.List;

public interface ContractService {

    List<ContractData> selectClientContracts(int id) throws DaoException;
    List<ContractData> selectManagerContracts(int id) throws DaoException;
    List<ContractData> selectUnconfirmedContracts() throws DaoException;

    boolean startRent(int id_user, int id_car) throws DaoException;
    boolean cancelRent(int idContract) throws DaoException;
    boolean confirmRent(int idContract, int idManager) throws DaoException;
    boolean finishRent(int idContract) throws DaoException;
}
