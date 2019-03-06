package by.epam.task05.dao;


import by.epam.task05.dao.impl.SQLCarDAO;
import by.epam.task05.dao.impl.SQLContractDAO;
import by.epam.task05.dao.impl.SQLUserDAO;

public class DAOProvider {
	private static final DAOProvider instance = new DAOProvider();
	
	private final UserDAO userDAO = new SQLUserDAO();
	private final CarDAO carDAO = new SQLCarDAO();
	private final ContractDAO contractDAO = new SQLContractDAO();

	
	private DAOProvider() {}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public CarDAO getCarDAO() {
		return carDAO;
	}
	public ContractDAO getContractDAO() {
		return contractDAO;
	}

	
	public static DAOProvider getInstance() {
		return instance;
	}

}
