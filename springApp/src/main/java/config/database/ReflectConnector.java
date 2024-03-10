package config.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ReflectConnector {
	private static final Logger logger = LoggerFactory.getLogger(ReflectConnector.class);

	public static Connection getConnection(String databaseName) throws SQLException {
		logger.debug("ReflectConnector getConnection: {}", databaseName);

		DatabaseLocator databaseLocator = DatabaseLocator.getInstance();
		databaseLocator.setDatabaseName(databaseName);

		return databaseLocator.getConnection();
	}
}