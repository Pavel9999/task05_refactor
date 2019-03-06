package by.epam.task05.service.impl;


import by.epam.task05.dao.DAOProvider;
import by.epam.task05.dao.DaoException;
import by.epam.task05.dao.UserDAO;
import by.epam.task05.entity.User;
import by.epam.task05.entity.UserData;
import by.epam.task05.logger.MyLogger;
import by.epam.task05.service.ClientService;
import by.epam.task05.service.ServiceException;
import by.epam.task05.service.validation.CredentionalValidator;

public class ClientServiceImpl implements ClientService {

	@Override
	public User authorization(String login, String password) throws ServiceException {
		
		if(!CredentionalValidator.isCorrect(login, password)) {
			throw new ServiceException();
		}
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		
		User user = null;
				
		try {		
			user =	userDAO.authentification(login, password);
		}catch(Exception e) {
			System.out.println(e);
			MyLogger.getInstance().error(e);
			throw new ServiceException();
		}
		
		return user;
	}

	@Override
	public boolean registration(UserData userData){

		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();

		try {
			if (!userDAO.checkEmailAvailability(userData.getEmail())) return false;
			userDAO.registration(userData);
		}catch(Exception e) {
			System.out.println(e);
			MyLogger.getInstance().error(e);
		}


		return true;
	}

	@Override
	public User getUser(String email){

		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();

		User user = null;

		try {
			user =	userDAO.getUser(email);
		}catch(Exception e) {
			System.out.println(e);
			MyLogger.getInstance().error(e);
		}

		return user;
	}

}
