package by.epam.task05.service.impl;

import by.epam.task05.dao.*;
import by.epam.task05.entity.*;
import by.epam.task05.service.ContractService;

import java.util.LinkedList;
import java.util.List;

public class ContractServiceImpl implements ContractService {

    @Override
    public List<ContractData> selectClientContracts(int id) throws DaoException {
        List<ContractData> contractDatas = new LinkedList<>();

        DAOProvider daoProvider = DAOProvider.getInstance();
        ContractDAO contractDAO = daoProvider.getContractDAO();

        List<Contract> contracts = contractDAO.selectClientContracts(id);
        for (int i = 0 ; i < contracts.size(); i++)
        {
            ContractData newData = createContractData(contracts.get(i));
            contractDatas.add(newData);
        }

        return contractDatas;
    }

    @Override
    public List<ContractData> selectManagerContracts(int id) throws DaoException {
        List<ContractData> contractDatas = new LinkedList<>();

        DAOProvider daoProvider = DAOProvider.getInstance();
        ContractDAO contractDAO = daoProvider.getContractDAO();

        List<Contract> contracts = contractDAO.selectManagerContracts(id);
        for (int i = 0 ; i < contracts.size(); i++)
        {
            ContractData newData = createContractData(contracts.get(i));
            contractDatas.add(newData);
        }

        return contractDatas;
    }

    @Override
    public List<ContractData> selectUnconfirmedContracts() throws DaoException {
        List<ContractData> contractDatas = new LinkedList<>();

        DAOProvider daoProvider = DAOProvider.getInstance();
        ContractDAO contractDAO = daoProvider.getContractDAO();

        List<Contract> contracts = contractDAO.selectContractsWithState(ContractState.UNCONFIRMED);
        for (int i = 0 ; i < contracts.size(); i++)
        {
            ContractData newData = createContractData(contracts.get(i));
            contractDatas.add(newData);
        }

        return contractDatas;
    }

    @Override
    public boolean startRent(int id_user, int id_car) throws DaoException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        CarDAO carDAO = daoProvider.getCarDAO();
        ContractDAO contractDAO = daoProvider.getContractDAO();

        Car car = carDAO.selectCar(id_car);

        Contract contract = new Contract();
        contract.setId_client(id_user);
        contract.setId_car(id_car);
        contract.setAll_price(car.getPrice());
        contract.setState(ContractState.UNCONFIRMED);
        contract.setDate_start("nothing");
        contract.setDate_finish("nothing");
        int id_contract = contractDAO.addContract(contract);

        carDAO.setContractID(id_car, id_contract);

        return true;
    }

    @Override
    public boolean cancelRent(int idContract) throws DaoException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        CarDAO carDAO = daoProvider.getCarDAO();
        ContractDAO contractDAO = daoProvider.getContractDAO();

        Contract contract = contractDAO.getContract(idContract);

        carDAO.setContractID(contract.getId_car(), 0);
        contractDAO.cancelContract(idContract);

        return true;
    }

    @Override
    public boolean confirmRent(int idContract, int idManager) throws DaoException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ContractDAO contractDAO = daoProvider.getContractDAO();

        contractDAO.confirmContract(idContract);
        contractDAO.setManager(idContract, idManager);

        return true;
    }

    @Override
    public boolean finishRent(int idContract) throws DaoException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        CarDAO carDAO = daoProvider.getCarDAO();
        ContractDAO contractDAO = daoProvider.getContractDAO();

        Contract contract = contractDAO.getContract(idContract);

        carDAO.setContractID(contract.getId_car(), 0);
        contractDAO.finishContract(idContract);

        return true;
    }


    private ContractData createContractData(Contract contract) throws DaoException {
        ContractData contractData = new ContractData();

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        CarDAO carDAO = daoProvider.getCarDAO();
        User client = userDAO.getUser(contract.getId_client());

        User manager = null;
        int id_manager = contract.getId_manager();
        if (id_manager > 0) manager = userDAO.getUser(contract.getId_manager());

        Car newCar = carDAO.selectCar(contract.getId_car());

        contractData.setId(contract.getId());
        contractData.setAll_price(contract.getAll_price());
        contractData.setCar(newCar.getFullName());
        contractData.setClient(client.getFull_name());
        if (manager!=null) contractData.setManager(manager.getFull_name());
        contractData.setState(contract.getState().toString());
        contractData.setDate_start(contract.getDate_start());
        contractData.setDate_finish(contract.getDate_finish());

        return contractData;

    }
}
