package by.epam.task05.dao.impl;

import by.epam.task05.dao.NotDBDriverException;
import by.epam.task05.logger.MyLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ConnectionPool {
	private static final ConnectionPool instance = new ConnectionPool();

	private List<Connection> connectionPool= new ArrayList<>();
	private List<Connection> usedConnections = new ArrayList<>();
	private static int INITIAL_POOL_SIZE = 10;

	protected static final String driver;
	protected static final String url;
	protected static final String login;
	protected static final String password;


	private static final String DB_DRIVER = "db.driver";
	private static final String DB_URL = "db.url";
	private static final String DB_LOGIN = "db.login";
	private static final String DB_PASSWORD = "db.password";

	private static final String DB_PROPERTIES_FILE_PATH = "db";



	protected ConnectionPool() {
		for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
			connectionPool.add(createConnection());
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}
	
	public Connection getConnection()
	{
		if (connectionPool.size() == 0)
		{
			connectionPool.add(createConnection());
		}

		Connection connection = connectionPool.remove(connectionPool.size() - 1);
		usedConnections.add(connection);
		return connection;
	}

	public boolean releaseConnection(Connection connection) {
		connectionPool.add(connection);
		return usedConnections.remove(connection);
	}

	public int getSize() {
		return connectionPool.size() + usedConnections.size();
	}

	private static Connection createConnection(){

		Connection con = null;
		try{
			con = DriverManager.getConnection(url, login, password);
		}
		catch (SQLException e)
		{
			MyLogger.getInstance().error(e);
		}

		return con;
	}



	static {

		ResourceBundle jdbcProperties = ResourceBundle.getBundle(DB_PROPERTIES_FILE_PATH);

		driver = jdbcProperties.getString(DB_DRIVER);
		url = jdbcProperties.getString(DB_URL);
		login = jdbcProperties.getString(DB_LOGIN);
		password = jdbcProperties.getString(DB_PASSWORD);

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			MyLogger.getInstance().error(e);
			throw new NotDBDriverException("Can't find driver.", e);
		}
	}

}
