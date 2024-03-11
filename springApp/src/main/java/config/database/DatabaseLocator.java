package config.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

public class DatabaseLocator implements ConnectionLocator {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String DATABASE_INFO_FILE = "/databaseInfo.properties";
	private static final String LOG4JDBC_DRIVER_SPY = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
	private static final String DEFAULT_DATABASE = "model2";

	private String databaseName;

	public DatabaseLocator() {
		setDatabaseName(DEFAULT_DATABASE);
	}

	public DatabaseLocator(String databaseName) {
		setDatabaseName(databaseName);
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public static DatabaseLocator getInstance() {
		return DatabaseLocatorHelper.INSTANCE;
	}

	private static class DatabaseLocatorHelper {
		private static final DatabaseLocator INSTANCE = new DatabaseLocator();
	}

	@Override
	public Connection getConnection() throws SQLException {
		logger.debug("DatabaseLocator getConnection: {}", getDatabaseName());

		Properties properties = loadDatabaseProperties();

		String url = properties.getProperty(getDatabaseName() + ".url");
		String user = properties.getProperty(getDatabaseName() + ".user");
		String password = properties.getProperty(getDatabaseName() + ".password");

		loadDatabaseDriver(properties.getProperty(getDatabaseName() + ".driver"));

		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(true);

		return connection;
	}

	private Properties loadDatabaseProperties() {
		Properties properties = new Properties();

		try (InputStream inputStream = DatabaseLocator.class.getResourceAsStream(DATABASE_INFO_FILE)) {
			if (inputStream == null) {
				logger.error("Unable to load database properties file: {}", DATABASE_INFO_FILE);
				throw new RuntimeException("Database properties file not found");
			}

			properties.load(inputStream);
		} catch (IOException e) {
			logger.error("loadDatabaseProperties IOException");
			throw new RuntimeException("loadDatabaseProperties IOException", e);
		}

		return properties;
	}

	private void loadDatabaseDriver(String driver) {
		try {
			Class.forName(driver);
			Class.forName(LOG4JDBC_DRIVER_SPY);
		} catch (ClassNotFoundException | NullPointerException e) {
			logger.error("Database driver not found: {}", driver);
			throw new RuntimeException("Database driver not found", e);
		}
	}

	/*
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		logger.debug("DatabaseLocator getConnection: {}", databaseName);

		String path = getClass().getResource("/databaseInfo.properties").getPath();
		FileReader resources = new FileReader(path);
		Properties properties = new Properties();
		properties.load(resources);

		String url = properties.getProperty(databaseName + ".url");
		String user = properties.getProperty(databaseName + ".user");
		String password = properties.getProperty(databaseName + ".password");

		Class.forName(properties.getProperty(databaseName + ".driver"));
		Class.forName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");

		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(true);
		resources.close();

		return connection;
	}
	*/
}