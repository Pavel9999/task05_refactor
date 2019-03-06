package by.epam.task05.dao.impl;


import by.epam.task05.entity.UserRole;
import by.epam.task05.dao.DaoException;
import by.epam.task05.dao.UserDAO;
import by.epam.task05.entity.User;
import by.epam.task05.entity.UserData;
import by.epam.task05.logger.MyLogger;

import java.sql.*;

public class SQLUserDAO extends SqlDao implements UserDAO {


	private static final String USER_CHECK_CREDENTIONALS = "SELECT * FROM user WHERE `e-mail`=? and `password`=?";
	private static final String EMAIL_CHECK_CREDENTIONALS = "SELECT * FROM user WHERE `e-mail`=?";
	private static final String ID_CHECK_CREDENTIONALS = "SELECT * FROM user WHERE `id_user`=?";

	private static final String INSERT_USER_CREDENTIONALS = "INSERT INTO user (`id_user`, `id_role`, `firstname`, `surname`, `lastname`, `e-mail`, `password`) Values (?, ?, ?, ?, ?, ?, ?)";
	
	
	private static final ConnectionPool pool = ConnectionPool.getInstance();




	@Override
	public User authentification(String userLogin, String userPassword) throws DaoException {
		Connection con = null;
		PreparedStatement st= null;
		ResultSet rs = null;
		
		User user = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();

			st = con.prepareStatement(USER_CHECK_CREDENTIONALS);

			st.setString(1, userLogin);
			st.setString(2, userPassword);
			
			rs = st.executeQuery();

			if(rs.next()) {
				user  = createUser(rs);
			}

			ConnectionPool.getInstance().releaseConnection(con);

		} catch (SQLException e) {
			System.out.println(e);
			MyLogger.getInstance().error(e);
			throw new DaoException(e);
		}
		finally {
			 close(rs, st);
		}

		return user;
	}

	@Override
	public boolean validation(String userLogin, String userPassword){
		Connection con = null;
		PreparedStatement st= null;
		ResultSet rs = null;

		boolean result = false;

		try {
			con = ConnectionPool.getInstance().getConnection(); //DriverManager.getConnection(url, login, password);

			st = con.prepareStatement(USER_CHECK_CREDENTIONALS);

			st.setString(1, userLogin);
			st.setString(2, userPassword);

			rs = st.executeQuery();

			if(rs.next()) {
				result  = true;
			}

			ConnectionPool.getInstance().releaseConnection(con);

		} catch (SQLException e) {
			System.out.println(e);
			MyLogger.getInstance().error(e);
		}
		finally {
			close(rs, st);
		}

		return result;
	}

	@Override
	public boolean registration(UserData userData) throws DaoException {
		Connection con = null;
		PreparedStatement st= null;
		ResultSet rs = null;

		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			con = DriverManager.getConnection(url, login, password);
			st = con.prepareStatement(INSERT_USER_CREDENTIONALS);

			st.setInt(1, getNextUserID());
			st.setInt(2, userData.getRole());
			st.setString(3, userData.getFirst_name());
			st.setString(4, userData.getSecond_name());
			st.setString(5, userData.getLast_name());
			st.setString(6, userData.getEmail());
			st.setString(7, userData.getPassword());

			ConnectionPool.getInstance().releaseConnection(con);

			try
			{
				st.executeUpdate();
			}
			catch (SQLException e) {
				System.out.println(e);
				MyLogger.getInstance().error(e);
			}
		} catch (Exception e)
		{
			System.out.println(e);
			MyLogger.getInstance().error(e);
		}
		finally {
			close(rs, st);
		}


		return true;
	}

	@Override
	public boolean checkEmailAvailability(String email) throws DaoException {
		boolean result = true;

		Connection con = null;
		PreparedStatement st= null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, login, password);
			st = con.prepareStatement(EMAIL_CHECK_CREDENTIONALS);
			st.setString(1, email);
			rs = st.executeQuery();


			if (rs.next()){
				result = false;
			}

			ConnectionPool.getInstance().releaseConnection(con);

		} catch (SQLException e) {
			System.out.println(e);
			MyLogger.getInstance().error(e);

			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e);
				MyLogger.getInstance().error(e);
				throw new DaoException(e);
			}
			throw new DaoException(e);
		} finally {
			close(rs, st);
		}


		return result;
	}

	@Override
	public User getUser(String email) throws DaoException {
		Connection con = null;
		PreparedStatement st= null;
		ResultSet rs = null;

		User user = null;

		try {
			con = DriverManager.getConnection(url, login, password);
			st = con.prepareStatement(EMAIL_CHECK_CREDENTIONALS);
			st.setString(1, email);
			rs = st.executeQuery();


			if (rs.next()){
				user = createUser(rs);
			}

			ConnectionPool.getInstance().releaseConnection(con);

		} catch (SQLException e) {
			System.out.println(e);
			MyLogger.getInstance().error(e);

			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1);
				MyLogger.getInstance().error(e1);
				throw new DaoException(e);
			}
			throw new DaoException(e);
		} finally {
			close(rs, st);
		}

		return user;
	}

	@Override
	public User getUser(int id) throws DaoException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		User user = null;

		try {
			con = DriverManager.getConnection(url, login, password);
			st = con.prepareStatement(ID_CHECK_CREDENTIONALS);
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()){
				user = createUser(rs);
			}

			ConnectionPool.getInstance().releaseConnection(con);

		} catch (SQLException e) {
			System.out.println(e);
			MyLogger.getInstance().error(e);

			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1);
				MyLogger.getInstance().error(e1);
				throw new DaoException(e);
			}
			throw new DaoException(e);
		} finally {
			close(rs, st);
		}

		return user;
	}

	int getNextUserID()
	{
		int id = 0;
		Connection con = null;
		PreparedStatement st= null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, login, password);
			String sqlRequest = "SELECT COUNT(*) FROM user";
			st = con.prepareStatement(sqlRequest);
			rs = st.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			}

			ConnectionPool.getInstance().releaseConnection(con);

		} catch (Exception e) {
			System.out.println(e);
			MyLogger.getInstance().error(e);
		}
		finally {
			close(rs, st);
		}


		return id;
	}



	private User createUser(ResultSet resultSet) throws SQLException {
		User user = new User();

		int id = resultSet.getInt(1);
		int role = resultSet.getInt(2);
		String first_name = resultSet.getString(3);
		String second_name = resultSet.getString(4);
		String last_name = resultSet.getString(5);
		String _mail = resultSet.getString(6);
		String _password = resultSet.getString(7);


		user.setId(id);
		user.setRole(UserRole.createRole(role));
		user.setFirst_name(first_name);
		user.setSecond_name(second_name);
		user.setLast_name(last_name);
		user.setEmail(_mail);
		user.setPassword(_password);

		return user;

	}

	private void close(ResultSet rs, PreparedStatement st) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
			MyLogger.getInstance().error(e);
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
			MyLogger.getInstance().error(e);
		}
	}

}
