package by.epam.task05.service.validation;

import by.epam.task05.dao.CarDAO;
import by.epam.task05.dao.DAOProvider;
import by.epam.task05.dao.UserDAO;

public class CredentionalValidator {
	
	public static boolean isCorrect(String login, String password) {

		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();

		return userDAO.validation(login, password);
	}

}
