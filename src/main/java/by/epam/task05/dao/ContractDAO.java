package by.epam.task05.dao;

import by.epam.task05.entity.Contract;
import by.epam.task05.entity.ContractState;

import java.util.List;

public interface ContractDAO
{
    Contract getContract(int id) throws DaoException;

    int addContract(Contract contract) throws DaoException;
    boolean cancelContract(int id) throws DaoException;
    boolean confirmContract(int id) throws DaoException;
    boolean finishContract(int id) throws DaoException;
    boolean setManager(int idContract, int idManager) throws DaoException;

    List<Contract> selectContractsWithState(ContractState state);
    List<Contract> selectClientContracts(int id);
    List<Contract> selectManagerContracts(int id);
}
