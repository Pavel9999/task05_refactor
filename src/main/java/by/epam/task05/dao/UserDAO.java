package by.epam.task05.dao;


import by.epam.task05.entity.User;
import by.epam.task05.entity.UserData;

public interface UserDAO {

	User authentification(String login, String password) throws DaoException;
	boolean validation(String userLogin, String userPassword);
	
	boolean registration(UserData userData)  throws DaoException;

	boolean checkEmailAvailability(String email) throws DaoException;

	User getUser(String email) throws DaoException;
	User getUser(int id) throws DaoException;
}
