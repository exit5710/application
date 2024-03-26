package config.database.reflection;

import config.database.DatabaseLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ReflectionConnector {
	private static final Logger logger = LoggerFactory.getLogger(ReflectionConnector.class);

	public static Connection getConnection(String databaseName) throws SQLException {
		logger.debug("ReflectConnector getConnection: {}", databaseName);

		DatabaseLocator databaseLocator = DatabaseLocator.getInstance();
		databaseLocator.setDatabaseName(databaseName);

		return databaseLocator.getConnection();
	}
}